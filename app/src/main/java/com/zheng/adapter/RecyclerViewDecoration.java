package com.zheng.adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Naqing on 9/26/17.
 * Recyclar view item decoration
 */

public class RecyclerViewDecoration extends RecyclerView.ItemDecoration {
    private final int mHeight;
    private final int mWidth;

    public RecyclerViewDecoration(int spacing) {
        this(spacing, spacing);
    }

    public RecyclerViewDecoration(int height, int width) {
        this.mHeight = height;
        this.mWidth = width;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = mHeight / 4;
        outRect.bottom = mHeight / 4;
        outRect.left = mWidth / 4;
        outRect.right = mWidth / 4;
    }
}
