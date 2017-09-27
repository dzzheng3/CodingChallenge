package com.zheng.injection.module;

import android.content.ContentResolver;

import com.zheng.data.service.Service;
import com.zheng.feature.main.ListPresenter;
import com.zheng.feature.main.MainActivity;
import com.zheng.feature.main.MainContract;
import com.zheng.injection.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Naiqing on 9/26/17.
 */

@Module
public class MainModule {

    private MainContract.ListView view;

    public MainModule(MainContract.ListView view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public MainContract.ListView providerView(){
        return view;
    }
    @ActivityScope
    @Provides
    public ListPresenter providerListPresenter(ContentResolver contentResolver, MainContract.ListView listView, Service service){
        return new ListPresenter(contentResolver,listView,service);
    }

}