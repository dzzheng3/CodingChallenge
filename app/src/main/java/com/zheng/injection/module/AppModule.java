package com.zheng.injection.module;

import android.content.ContentResolver;
import android.content.Context;

import com.zheng.MyApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Naiqing on 9/26/17.
 */

@Module
public class AppModule {

    private Context context;

    public AppModule(MyApplication application) {
        this.context = application;
    }

    @Singleton
    @Provides
    public Context ProviderApplicationContext() {
        return context;
    }

    //
    @Singleton
    @Provides
    public ContentResolver providerContentResolver() {
        return context.getContentResolver();
    }

}