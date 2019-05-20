package com.example.project_practice.utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MessageItemDecoration extends RecyclerView.ItemDecoration {
    //设置画线的长度和高度
    private int dividerHeight = 1;
    //画笔
    private Paint dividerPaint;

    //左边有多少不画？
    //右边有多少不画？
    private int left_margin;
    private int right_margin;

    //一个星标好友
    private int starNum;

    public MessageItemDecoration() {
        dividerPaint = new Paint();
        dividerPaint.setColor(Color.GRAY);

        left_margin = 110;
        right_margin = 15;
        starNum = 1;

    }

    /**
     * 画线
     *
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft() + left_margin;
        int right = parent.getWidth() - parent.getPaddingRight() - right_margin;

        for (int i = 0; i <= childCount - 1; i++) {
            View view = parent.getChildAt(i);
            float top = view.getBottom();
            float bottom = view.getBottom() + dividerHeight;
            c.drawRect(left, top, right, bottom, dividerPaint);
        }
    }

    /**
     * 设置条目周边的偏移量
     *
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
//        outRect.bottom = 30;
        //或者
        outRect.set(0, 0, 0, dividerHeight);
    }
}
