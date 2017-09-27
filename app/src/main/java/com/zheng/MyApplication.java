package com.zheng;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.LeakTrace;
import com.zheng.injection.component.AppComponent;
import com.zheng.injection.component.DaggerAppComponent;
import com.zheng.injection.module.AppModule;
import com.zheng.injection.module.NetModule;

/**
 * Created by Naiqing on 9/26/17.
 */

public class MyApplication extends Application {
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);


    }

    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    private void setupApplicationComponent() {
        //Dagger开头的注入类DaggerAppComponent
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        if(appComponent == null){
            this.setupApplicationComponent();
        }
        return appComponent;
    }
}
