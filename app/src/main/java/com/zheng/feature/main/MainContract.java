package com.zheng.feature.main;

import com.zheng.data.model.AlbumItem;

import java.util.ArrayList;

/**
 * Created by Naiqing on 9/26/17.
 */

public interface MainContract {
    interface ListView{

        void initializeView();
        void loadEventList(ArrayList<AlbumItem> albumItems);
        void showProgress();
        void hideProgress();
        void showMessage(String messge);

    }

    interface  presenter{

    }
}
