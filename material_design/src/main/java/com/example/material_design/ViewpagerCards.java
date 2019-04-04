package com.example.material_design;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;

/**
 * 模仿ViewpagerCards的页面
 * 它与CardViewActivity的不同在于：这个加了一点放大效果，加了一点
 */
public class ViewpagerCards extends AppCompatActivity {
    private static final String TAG = "ViewpagerCards";
    ViewPager viewPager;
//    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_cards);

        viewPager = findViewById(R.id.ViewPagerCards_viewPager);
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        //以上两行代码就是一个viewPager的建立了，（加上tab_Layout就是一个场景的滑动效果了）。
        //但是问题是：没办法同时显示3个Fragment。更别说在左侧堆积Fragment了

        //修改了Fragment的布局文件，现在能显示3个了，看来问题在于布局文件。在布局文件的什么地方呢？   ->   在于它是否是CardView布局，为什么呢？其他布局为什么不行呢？/\  clipToPadding的作用：ViewPager的两边留白，是否会被用来显示两边控件的部分内容
        //(笑，不是其他布局，只是其他布局你没有看出来而已。设置了一个颜色你就看出来了。

        //下一个问题是：为什么复制了人家的布局文件，但是我的卡片间隔比人家小很多？
        //问题在fragment里的 ： cardView.setMaxCardElevation(100);  中，设置越大，它的能设置的阴影就越大，但是呢，阴影要大，留出的空白就要多，所以这个地方设置大了，留白就多了  || 但是我在Fragment2的xml中设置android:elevation="80dp" 没有任何用？？？在代码中设置才有用？？？

        //现在做一个简单的放大动画
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                //如果想，即使在滑动的过程中，也要进行放大的操作，所以不用onPageSelected

            }

            @Override
            public void onPageSelected(int i) {
                //直接用这个的问题是，不会变回去。。。就是设置了新的XY之后，不会变回去
                CardView cardView = adapter.getCardViewAt(i);
                cardView.animate().scaleX(1.1f);
                cardView.animate().scaleY(1.1f);
                cardView.setElevation(36);

                //嫌弃，不想用循环啊，总觉得效率效率效率
                adapter.resetCardXY(i);
                //要么就设置一个对象用于保存上一次的CardView，这样也行

                //上述代码完成后的问题就是：如何在滑动的过程中也出现动画效果呢？这就参见Sample了，在onPageScrolled中设置就好了！

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }
}
