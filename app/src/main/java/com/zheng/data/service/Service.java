package com.zheng.data.service;


import com.zheng.data.model.AlbumItem;

import java.util.ArrayList;

import retrofit2.http.GET;
import rx.Observable;

/**
 *Created by Naqing on 9/26/17.
 */

public interface Service {
    String List_URL = "https://s3.amazonaws.com/sc.va.util.weatherbug.com/interviewdata/mobilecodingchallenge/";

    @GET("sampledata.json")
    Observable<ArrayList<AlbumItem>> getAlbumItemList();
}
