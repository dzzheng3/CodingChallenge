package com.zheng.util;

import android.app.Activity;
import android.content.Intent;

import com.bumptech.glide.request.RequestOptions;
import com.zheng.data.model.AlbumItem;

import zheng.com.naiqingapp.R;


public class Util {

    //Constant declaration
    public static String EVENT_DATA = "event_data";


    public static RequestOptions Placeholder(){
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.placeholder_nomoon);
        requestOptions.error(R.drawable.placeholder_nomoon);
        return requestOptions;
    }
}
