package com.zheng.injection.component;

import com.zheng.injection.module.AppModule;
import com.zheng.injection.module.MainModule;
import com.zheng.injection.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Naiqing on 9/26/17.
 */
@Singleton
@Component(modules = {NetModule.class, AppModule.class})
public interface AppComponent {
    MainComponent addSub(MainModule mainModule);
}
