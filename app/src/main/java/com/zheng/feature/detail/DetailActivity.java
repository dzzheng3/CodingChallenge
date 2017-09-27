package com.zheng.feature.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zheng.data.model.AlbumItem;
import com.zheng.data.service.Service;
import com.zheng.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import zheng.com.naiqingapp.R;

/**
 * Created by Naqing on 9/26/17.
 */

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = DetailActivity.class.getSimpleName();
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_sub_title)
    TextView tvSubTitle;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    @BindView(R.id.cp_toolbar)
    CollapsingToolbarLayout cpToolbar;
    private AlbumItem mAlbumItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(DetailActivity.this);

        //Intent data is coming from RecyclerViewAdapter
        Intent intent = getIntent();
        if (intent != null)
            mAlbumItem = intent.getParcelableExtra(Util.EVENT_DATA);
        initToolbar();
        setData();
    }

    /**
     * setting data to view coming from ui.activity click event adapter
     */
    private void setData() {

        if (mAlbumItem != null && !TextUtils.isEmpty(mAlbumItem.getTitle()))
            tvSubTitle.setText(mAlbumItem.getTitle());
        else
            tvSubTitle.setVisibility(View.GONE);

        if (mAlbumItem != null && !TextUtils.isEmpty(mAlbumItem.getDescription()))
            tvDescription.setText(mAlbumItem.getDescription());
        else
            tvDescription.setVisibility(View.GONE);


        /**
         * setDefaultRequestOptions used to support placeholder and error image
         * when something went wrong with url
         */
        if (mAlbumItem != null && !TextUtils.isEmpty(mAlbumItem.getmFilename())) {
            Glide.with(DetailActivity.this)
                    .setDefaultRequestOptions(Util.Placeholder())
                    .load(Service.List_URL+mAlbumItem.getmFilename())
                    .into(image);
        }
    }

    private void initToolbar() {
        toolbar.setTitle(" ");
        setSupportActionBar(toolbar);
    }




}
