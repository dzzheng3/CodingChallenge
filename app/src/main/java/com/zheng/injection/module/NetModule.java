package com.zheng.injection.module;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.readystatesoftware.chuck.ChuckInterceptor;
import com.zheng.data.service.Service;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Naiqing on 9/26/17.
 */
@Module
public class NetModule {
    private Context context;

    public NetModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    public Gson getGson() {
        return new GsonBuilder()
                .create();
    }

    @Singleton
    @Provides
    public OkHttpClient getOkHttpClient() {
        //setup cache
        File httpCacheDirectory = new File(context.getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        return new OkHttpClient.Builder()
                .addInterceptor(new ChuckInterceptor(context))
                .cache(cache)
                .build();
    }

    @Singleton
    @Provides
    public Service getService(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Service.List_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(Service.class);
    }
}