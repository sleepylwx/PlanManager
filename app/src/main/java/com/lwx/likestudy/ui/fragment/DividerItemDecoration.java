package com.lwx.likestudy.ui.fragment;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 36249 on 2016/10/27.
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private static final int HEIGHT = 3;

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state){

        super.getItemOffsets(outRect,view,parent,state);
        outRect.bottom = HEIGHT;
    }
}
