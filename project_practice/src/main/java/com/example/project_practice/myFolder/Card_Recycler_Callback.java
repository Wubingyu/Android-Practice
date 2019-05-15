package com.example.project_practice.myFolder;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.project_practice.R;

public class Card_Recycler_Callback extends ItemTouchHelper.Callback {
    //解耦设计：因为滑动和移除涉及到数据交换，抽象出方法，然后让RecyclerAdapter实现这个抽象。
    //其实我不是特别理解怎么想到的，这个又是什么思想，但是的确非常好用。这方面的思想应该在哪里能系统的学习到呢？
    private final ItemTouchHelperAdapter mAdapter;

    public Card_Recycler_Callback(ItemTouchHelperAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    /**
     * ItemTouchHelper可以让你轻易得到一个事件的方向。你需要重写getMovementFlags()方法来指定可以支持的拖放和滑动的方向。
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        mAdapter.onItemMove(viewHolder.getAdapterPosition(),
                viewHolder1.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    //设置滑动item的背景
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        //判断选中状态
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            viewHolder.itemView.setBackgroundColor(viewHolder.itemView.getContext().getResources().getColor(R.color.grey_300));
    }
        super.onSelectedChanged(viewHolder, actionState);

    }

    //清除滑动item的背景
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        // 恢复
        viewHolder.itemView.setBackgroundColor(Color.WHITE);
        super.clearView(recyclerView, viewHolder);
    }

}
