package com.zheng.feature.main;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.zheng.data.database.AlbumContract;
import com.zheng.data.model.AlbumItem;
import com.zheng.data.service.Service;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by dzzheng3 on 9/26/17.
 */

public  class ListPresenter implements MainContract.presenter{
    private static final String TAG = ListPresenter.class.getSimpleName();
    private ContentResolver contentResolver;
    private MainContract.ListView listView;
    private Service service;

    public ListPresenter(ContentResolver contentResolver, MainContract.ListView listView,Service service) {
        this.contentResolver = contentResolver;
        this.listView = listView;
        this.service = service;
        listView.initializeView();
    }


    public void loadData() {
        listView.showProgress();
        service.getAlbumItemList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ArrayList<AlbumItem>>() {
                    @Override
                    public void call(ArrayList<AlbumItem> albumItems) {
//                        Log.e(TAG, albumItems.get(0).toString());
                        listView.loadEventList(albumItems);
                        insertEventsInDatabase(albumItems);
                        listView.hideProgress();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        listView.hideProgress();
                        listView.showMessage("No internet connection!");
//                        Log.e(TAG, "Unable to download new ui.activity of events", throwable);
                        if (contentResolver != null) {
                            Cursor cursor = contentResolver.query(AlbumContract.getContentUri(), null, null, null, null);
                            ArrayList<AlbumItem> albumItems = createEventList(cursor);
                            listView.loadEventList(albumItems);
                        }
                    }
                });
    }

    private void insertEventsInDatabase(List<AlbumItem> albumItems) {
        if (albumItems != null && albumItems.size() > 1) {
            Observable
                    .just(contentResolver.bulkInsert(AlbumContract.getContentUri(), getContentValues(albumItems)))
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Action1<Integer>() {
                        @Override
                        public void call(Integer integer) {
                            if (integer > 0) {
                                Log.d(TAG, "Successfully inserted albumItems into database");
                            }
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            Log.e(TAG, "Could not insert albumItems into database", throwable);
                        }
                    });
        }
    }

    private ArrayList<AlbumItem> createEventList(Cursor cursor) {
        ArrayList<AlbumItem> albumItemList = new ArrayList<>();
        while (cursor.moveToNext()) {
            albumItemList.add(new AlbumItem(cursor));
        }
        return albumItemList;
    }

    private ContentValues[] getContentValues(List<AlbumItem> albumItems) {
        ContentValues[] values = new ContentValues[albumItems.size()];
        for (int i = 0; i < albumItems.size(); i++) {
            values[i] = albumItems.get(i).createContentValues();
        }
        return values;
    }
    public void Destroy() {
        listView = null;
    }

}
