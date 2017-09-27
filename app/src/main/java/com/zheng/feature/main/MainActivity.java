package com.zheng.feature.main;


import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zheng.MyApplication;
import com.zheng.adapter.RecyclerViewAdapter;
import com.zheng.adapter.RecyclerViewDecoration;
import com.zheng.data.database.AlbumContract;
import com.zheng.data.model.AlbumItem;
import com.zheng.data.service.Service;
import com.zheng.injection.module.MainModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import zheng.com.naiqingapp.R;


public class MainActivity extends AppCompatActivity implements MainContract.ListView {

    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.empty)
    TextView empty;
    @BindView(R.id.list)
    RecyclerView recyclerView;
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.relative_layout)
    RelativeLayout relativeLayout;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    @Inject
    ListPresenter listPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        listPresenter = new ListPresenter(MainActivity.this, this);
        MyApplication.get(this)
                .getAppComponent()
                .addSub(new MainModule(this))
                .inject(this);
        listPresenter.loadData();
        requestPermission();
    }


    @Override
    public void initializeView() {

        // Retrieve the AppCompact Toolbar
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        // seeting recyclarview itemdecration
        recyclerView.addItemDecoration(new RecyclerViewDecoration(getResources().getDimensionPixelSize(R.dimen.card_spacing)));
    }

    @Override
    public void loadEventList(ArrayList<AlbumItem> albumItems) {
        // seeting recyclarview adapter
        mRecyclerViewAdapter = new RecyclerViewAdapter(albumItems);
        recyclerView.setAdapter(mRecyclerViewAdapter);

    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String messsage) {
        // shoeing message when you are not connected with internet
        Snackbar snackbar = Snackbar
                .make(relativeLayout, messsage, Snackbar.LENGTH_LONG)
                .setAction(R.string.retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listPresenter.loadData();
                    }
                });

        // Changing message text color
        snackbar.setActionTextColor(Color.RED);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mRecyclerViewAdapter.clear();
        recyclerView.setLayoutManager(new GridLayoutManager(this, newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ? 2 : 1));
        listPresenter.loadData();


    }

    @Override
    protected void onDestroy() {
        // Destroying view when we do rotation to prevent from memory leak
        listPresenter.Destroy();
        super.onDestroy();
    }

    /**
     * Created by Naqing on 9/26/17.
     */

    public void requestPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                String[] strArray={android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(strArray, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this,"WRITE_EXTERNAL_STORAGE ALLOWED",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,"WRITE_EXTERNAL_STORAGE NOT ALLOWED",Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
