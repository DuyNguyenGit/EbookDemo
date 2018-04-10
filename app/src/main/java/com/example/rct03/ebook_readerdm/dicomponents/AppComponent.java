package com.example.rct03.ebook_readerdm.dicomponents;


import com.example.rct03.ebook_readerdm.App;
import com.example.rct03.ebook_readerdm.modules.AppModule;
import com.example.rct03.ebook_readerdm.modules.NetModule;
import com.example.rct03.ebook_readerdm.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {

    void inject(App app);

    void inject(MainActivity mainActivity);


}
