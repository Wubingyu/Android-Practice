package com.example.diarylist.CustomView;

/**
 * created by yhao on 2017/8/11.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.example.diarylist.RecyclerView_Adapter;
import com.example.diarylist.RecyclerView_item;


public class SlidingMenu extends HorizontalScrollView {
    private static final String TAG = "SlidingMenu";


    //菜单占屏幕宽度比
    private static final float radio = 0.3f;
    private final int mScreenWidth;
    private final int mMenuWidth;


    private boolean once = true;
    private boolean isOpen;

    public SlidingMenu(final Context context, AttributeSet attrs) {
        super(context, attrs);
        mScreenWidth = ScreenUtil.getScreenWidth(context);
        mMenuWidth = (int) (mScreenWidth * radio);
        setOverScrollMode(View.OVER_SCROLL_NEVER);
        setHorizontalScrollBarEnabled(false);
    }

    /**
     * 问题：这里的问题在于：或许由于事件的分发机制，所以click事件，是隶属于ACTION_DOWN事件中的，然后因为我的click事件都在activity中处理了，（不知道具体应该是分发事件的哪一层）
     * 于是：这里接收不到ACTION_DOWN事件
     */

    /**
     * 关闭菜单
     */

    public void closeMenu() {
        this.smoothScrollTo(0, 0);
        isOpen = false;
    }

    /**
     * 菜单是否打开
     */
    public boolean isOpen() {
        return isOpen;
    }


    /**
     * 获取 adapter
     */
    private RecyclerView_Adapter getAdapter() {
        View view = this;
        while (true) {
            view = (View) view.getParent();
            if (view instanceof RecyclerView) {
                break;
            }
        }
        return (RecyclerView_Adapter) ((RecyclerView) view).getAdapter();
    }

    /**
     * 当打开菜单时记录此 view ，方便下次关闭
     */
    public void onOpenMenu() {
        getAdapter().holdOpenMenu(this);
        isOpen = true;
    }

    /**
     * 当触摸此 item 时，关闭上一次打开的 item
     */
    public void closeOpenMenu() {
        if (!isOpen) {
            getAdapter().closeOpenMenu();
        }
    }

//    /**
//     * 获取正在滑动的 item
//     */
//    public SlidingMenu getScrollingMenu() {
//        return getAdapter().getScrollingMenu();
//    }
//
//    /**
//     * 设置本 item 为正在滑动 item
//     */
//    public void setScrollingMenu(SlidingMenu scrollingMenu) {
//        getAdapter().setScrollingMenu(scrollingMenu);
//    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (once) {
            LinearLayout wrapper = (LinearLayout) getChildAt(0);
            //第一个child是context
            //第二个是menu，长度自设定
            wrapper.getChildAt(0).getLayoutParams().width = mScreenWidth;
            wrapper.getChildAt(1).getLayoutParams().width = mMenuWidth;
            once = false;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        //怎么禁止同时打开多个 item 呢？
        // 首先要明白所谓的“同时”并不可能真正同时，肯定有先后之分，
        // 我们可以像记录已打开的 item 一样，在 Adapter 中记录第一个开始滑动的 item ，
        // 而之后的 item 滑动前先判断一下 Adapter 中是否有记录 ，如果有正在滑动的 item 就禁止滑动，
        // 正在滑动 item 的记录周期从 ACTION_DOWN 开始，至 ACTION_UP 结束。首先是 SlidingMenu 的 onTouchEvent 方法：
        Log.d(TAG, "onTouchEvent: ");
//        Log.d(TAG, "onTouchEvent: ACTION_DOWN is: " + MotionEvent.ACTION_DOWN);
//        Log.d(TAG, "onTouchEvent: ACTION_DOWN is: " + MotionEvent.ACTION_UP);
        Log.d(TAG, "onTouchEvent: the ev.getAction is: " + ev.getAction());

//        if (getScrollingMenu() != null && getScrollingMenu() != this) {
//            return false;
//        }
        switch (ev.getAction()) {
            /**
             * 怀疑是子项的子控件的点击事件把消息拦截了，所以会接受不到DOWN事件，所以只好用UP事件来代替关闭之前打开的OpenMenu操作
             */
            case MotionEvent.ACTION_DOWN:
//                Log.d(TAG, "onTouchEvent: ACTION DOWN");
                downTime = System.currentTimeMillis();
                closeOpenMenu();
//                setScrollingMenu(this);
                break;
            case MotionEvent.ACTION_UP:
                closeOpenMenu();
//                setScrollingMenu(null);
                int scrollX = getScrollX();
                if (System.currentTimeMillis() - downTime <= 100 && scrollX == 0) {
                    if (mCustomOnClickListener != null) {
                        mCustomOnClickListener.onClick();
                    }
                    return false;
                }
                if (Math.abs(scrollX) > mMenuWidth / 2) {
                    this.smoothScrollTo(mMenuWidth, 0);
                    onOpenMenu();
                } else {
                    this.smoothScrollTo(0, 0);
                }
                return false;
        }
        return super.onTouchEvent(ev);
    }
    long downTime = 0;


    interface CustomOnClickListener {
        void onClick();
    }

    private CustomOnClickListener mCustomOnClickListener;

    void setCustomOnClickListener(CustomOnClickListener listener) {
        this.mCustomOnClickListener = listener;
    }

    public int getmScreenWidth() {
        return mScreenWidth;
    }

    public int getmMenuWidth() {
        return mMenuWidth;
    }
}
