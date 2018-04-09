package com.example.rct03.ebook_readerdm;

import android.app.Application;

public class App extends Application {
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        /*Init Dagger DI*/
        appComponent = DaggerAppComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(this))
                .build();

        getAppComponent().inject(this);
    }


    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
