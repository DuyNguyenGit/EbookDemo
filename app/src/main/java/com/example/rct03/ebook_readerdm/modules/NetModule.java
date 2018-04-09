package com.example.rct03.ebook_readerdm.modules;


import android.app.Application;

import com.example.rct03.ebook_readerdm.EbookApi;
import com.example.rct03.ebook_readerdm.data_service.UserService;
import com.example.rct03.ebook_readerdm.data_service.UserServiceImpl;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.rct03.ebook_readerdm.EbookApi.BASE_URL;

@Module
public class NetModule {

    public NetModule() {

    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
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
        return new OkHttpClient();
    }

    @Provides
    @Singleton
    public EbookApi provideEbookApi(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(EbookApi.class);
    }


    @Provides
    @Singleton
    UserService provideUserService(UserServiceImpl userService) {
        return userService;
    }
}
