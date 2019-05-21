package com.example.project_practice.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.project_practice.Explore.Explore_list_adapter;

public class CardItemTouchHelperCallback extends ItemTouchHelper.Callback {
    CardSwipeAdapter adapter;

    public CardItemTouchHelperCallback(Explore_list_adapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlags = 0;
        int swipeFlags = 0;
//        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
//        if (layoutManager instanceof CardLayoutManager) {
            swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
//        }
        return makeMovementFlags(dragFlags, swipeFlags);
    }


    //设置屏蔽拖动？可是这个全部都设置为false了？不弄个position判断吗？在LayoutManager中mOnTouchListener设置的
    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }


    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        viewHolder.itemView.setOnTouchListener(null);
        adapter.onSwipe();
    }
}
