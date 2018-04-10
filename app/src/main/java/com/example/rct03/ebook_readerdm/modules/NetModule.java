package com.example.rct03.ebook_readerdm.modules;


import android.app.Application;

import com.example.rct03.ebook_readerdm.api.EbookApi;
import com.example.rct03.ebook_readerdm.dataservices.EbookService;
import com.example.rct03.ebook_readerdm.dataservices.EbookServiceImpl;
import com.example.rct03.ebook_readerdm.dataservices.UserService;
import com.example.rct03.ebook_readerdm.dataservices.UserServiceImpl;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

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
        httpClient.cache(cache).addInterceptor(chain -> {
            Request original = chain.request();

            // Request customization: add request headers
            Request.Builder requestBuilder = original.newBuilder()
                    .header("x-project", "sap-press"); // <-- this is the important line

            Request request = requestBuilder.build();
            return chain.proceed(request);
        });
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

}
