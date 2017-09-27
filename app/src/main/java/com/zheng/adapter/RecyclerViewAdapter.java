package com.zheng.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zheng.data.model.AlbumItem;
import com.zheng.data.service.Service;
import com.zheng.feature.detail.DetailActivity;
import com.zheng.util.Util;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import zheng.com.naiqingapp.R;

/**
 * Created by Naqing on 9/26/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<AlbumItem> mValues;
    private Context context;

    public RecyclerViewAdapter(ArrayList<AlbumItem> mValues) {
        this.mValues = mValues;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        int orientation = parent.getContext().getResources().getConfiguration().orientation;
        View v;
        if(Configuration.ORIENTATION_PORTRAIT == orientation){
            v = LayoutInflater.from(context).inflate(R.layout.item_album, parent, false);
        }else {
            v = LayoutInflater.from(context).inflate(R.layout.item_album, parent, false);
        }

        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        AlbumItem mAlbumItem = mValues.get(position);
        if (!TextUtils.isEmpty(mAlbumItem.getTitle())) {
            holder.title.setVisibility(View.VISIBLE);
            holder.title.setText(mAlbumItem.getTitle());

        }
        if (!TextUtils.isEmpty(Service.List_URL+mAlbumItem.getmFilename())) {
            Glide.with(context)
                    .setDefaultRequestOptions(Util.Placeholder())
                    .load(Service.List_URL+mAlbumItem.getmFilename())
                    .into(holder.image);
        }
    }

    @Override
    public int getItemCount() {
        if (mValues == null) {
            return 0;
        }
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.image)
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(Util.EVENT_DATA, mValues.get(getLayoutPosition()));
                context.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, (View) image, "image").toBundle());
                ((Activity) context).overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
        }
    }
    public void clear() {
        int size = this.mValues.size();
        this.mValues.clear();
        notifyItemRangeRemoved(0, size);
    }
}

