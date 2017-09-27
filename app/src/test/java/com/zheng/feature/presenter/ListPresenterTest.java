package com.zheng.feature.presenter;

import android.content.ContentResolver;
import android.os.UserManager;

import com.google.gson.Gson;
import com.zheng.data.model.AlbumItem;
import com.zheng.data.service.Service;
import com.zheng.feature.main.ListPresenter;
import com.zheng.feature.main.MainContract;
import com.zheng.feature.main.MainActivity;
import com.zheng.injection.module.NetModule;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RuntimeEnvironment;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import rx.Observable;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.functions.Func1;
import rx.plugins.RxJavaHooks;
import rx.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

/**
 * Created by Administrator on 9/26/17.
 */
public class ListPresenterTest {
    private ContentResolver contentResolver;
    private MainContract.ListView listView;
    private ListPresenter presenter;
    private Service service;

    @Before
    public void setup(){

        RxUnitTestTools.openRxTools();
        contentResolver = Mockito.mock(ContentResolver.class);
        listView = Mockito.mock(MainContract.ListView.class);
        service = Mockito.mock(Service.class);
        presenter = new ListPresenter(contentResolver,listView,service);
    }
    @Test()
    public void loadData(){
        ArrayList<AlbumItem> list = new ArrayList<>();
        list.add(new AlbumItem("pexels photo 164196 jpeg","pexels photo 164196.jpeg","pexels-photo-164196.jpeg"));
        Mockito.when(service.getAlbumItemList()).thenReturn(Observable.just(list));
        presenter.loadData();
        Mockito.verify(listView,Mockito.times(1)).initializeView();
    }


    @After
    public void tearDown() throws Exception {
        RxJavaHooks.reset();
        RxAndroidPlugins.getInstance().reset();
    }
}