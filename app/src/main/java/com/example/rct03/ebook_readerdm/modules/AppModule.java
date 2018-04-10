package com.example.rct03.ebook_readerdm.modules;

import android.app.Application;
import android.content.Context;

import com.example.rct03.ebook_readerdm.App;
import com.example.rct03.ebook_readerdm.datamanager.DbHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


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
