package com.example.rct03.ebook_readerdm.modules;


import android.app.Application;
import android.util.Log;

import com.example.rct03.ebook_readerdm.api.EbookApi;
import com.example.rct03.ebook_readerdm.dataservices.EbookService;
import com.example.rct03.ebook_readerdm.dataservices.EbookServiceImpl;
import com.example.rct03.ebook_readerdm.dataservices.UserService;
import com.example.rct03.ebook_readerdm.dataservices.UserServiceImpl;
import com.example.rct03.ebook_readerdm.netservices.Download;
import com.example.rct03.ebook_readerdm.netservices.DownloadProgressInterceptor;
import com.example.rct03.ebook_readerdm.netservices.DownloadProgressListener;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.rct03.ebook_readerdm.api.EbookApi.BASE_URL;

@Module
public class NetModule {

    private static final String TAG = NetModule.class.getSimpleName();
    private static final long DEFAULT_TIMEOUT = 15;

    public NetModule() {

    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        DownloadProgressInterceptor interceptor = new DownloadProgressInterceptor(listener);
        httpClient.cache(cache).addInterceptor(interceptor)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        return httpClient.build();
    }

    @Provides
    @Singleton
    public EbookApi provideEbookApi(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(EbookApi.class);
    }

    @Provides
    @Singleton
    UserService provideUserService(EbookApi api) {
        return new UserServiceImpl(api);
    }

    @Provides
    @Singleton
    EbookService provideEbookService(EbookApi api) {
        return new EbookServiceImpl(api);
    }


    DownloadProgressListener listener = (bytesRead, contentLength, done) -> {
        Download download = new Download();
        download.setTotalFileSize(contentLength);
        download.setCurrentFileSize(bytesRead);
        int progress = (int) ((bytesRead * 100) / contentLength);
        download.setProgress(progress);

        sendNotification(download);
    };

    private void sendNotification(Download download) {
        Log.e(TAG, "sendNotification: >>>" + download.getProgress() + "%");
    }
}
