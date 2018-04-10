package com.example.rct03.ebook_readerdm;

import android.app.Application;
import android.content.Context;

import com.example.rct03.ebook_readerdm.data_manager.DbHelper;
import com.example.rct03.ebook_readerdm.data_service.UserService;
import com.example.rct03.ebook_readerdm.data_service.UserServiceImpl;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.rct03.ebook_readerdm.EbookApi.BASE_URL;


@Module
public class AppModule {

    private App app;

    public AppModule(App app) {
        this.app = app;
    }


    @Provides
    @Singleton
    Application provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    DbHelper provideDataBase(Context context) {
        return new DbHelper(context);
    }

}
