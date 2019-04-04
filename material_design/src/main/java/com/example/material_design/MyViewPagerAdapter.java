package com.example.material_design;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPager + TabLayout + Fragment 的结合使用
 * 在引导页中我们常常用 ViewPager 和 Fragment 结合使用，
 * 而像新闻分类的页面我们会再加上一个 TabLayout 三者联动使用。
 * 而此时，我们不会再使用 PagerAdapter 了，而是直接使用官方提供的专门用于与 Fragment 结合使用的 FragmentPagerAdapter。
 *
 * FragmentPagerAdapter 它将每一个页面表示为一个 Fragment，
 * 并且每一个 Fragment 都将会保存到 FragmentManager 当中。
 * 而且，当用户没可能再次回到页面的时候，FragmentManager 才会将这个 Fragment 销毁。
 *
 * 使用它需要两个方法：getItem 和 getCount
 * 但是使用PagerAdapter需要4个方法。
 *
 * 构造函数中关于Manager
 * MyViewPagerAdapter adapter = new MyViewPagerAdapter(getSupportFragmentManager());
 */
public class MyViewPagerAdapter extends FragmentPagerAdapter {
    //联合使用的时候，每个分页的title，都和TabLayout的Tab的设置无关了，全看adapter里的getPageTitle方法
    String[] tabTitleArray = {"第一个", "第二个", "第三个"};
    //这个list也可以从activity初始化的时候传过来
    List<CardFragment> fragments = new ArrayList<>();

    public MyViewPagerAdapter(FragmentManager fm) {
        super(fm);

        Fragment1 fragment1 = new Fragment1();
        fragment2 fragment2 = new fragment2();
        fragment3 fragment3 = new fragment3();
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);

    }

    /**
     * 返回的是对应的 Fragment 实例，一般我们在使用时，会通过构造传入一个要显示的 Fragment 的集合，我们只要在这里把对应的 Fragment 返回就行了。
     * @param i
     * @return
     */
    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    /**
     * getCount(); 是获取当前窗体界面数，也就是数据的个数。
     * @return
     */
    @Override
    public int getCount() {
        return tabTitleArray.length;
    }

//    /**
//     * 这个方法用于判断是否由对象生成界面，官方建议直接返回 return view == object;。
//     * @param view
//     * @param o
//     * @return
//     */
//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
//        return view == o;
//    }
//
//
//    /**
//     * 要显示的页面或需要缓存的页面，会调用这个方法进行布局的初始化。
//     * @param container
//     * @param position
//     * @return
//     */
//    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        return super.instantiateItem(container, position);
//    }
//
//    /**
//     * 如果页面不是当前显示的页面也不是要缓存的页面，会调用这个方法，将页面销毁。
//     * @param container
//     * @param position
//     * @param object
//     */
//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
//    }


    /**
     * TabLayout 也是通过 setupWithViewPager() 方法底部会调用 PagerAdapter 中的getPageTitle() 方法来实现联动的。
     * title就是tab的setText里的内容
     * @param position
     * @return
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitleArray[position];
    }

    public CardView getCardViewAt(int position) {
        return fragments.get(position).getCardView();
    }

    public void resetCardXY(int position) {
        List<CardView> cardViews = new ArrayList<>();
        for (int i = 0; i < getCount(); i++) {
            if (i != position) {
                CardView cardView = getCardViewAt(i);
                cardView.animate().scaleX(1);
                cardView.animate().scaleY(1);
                cardView.setElevation(24);

            }
        }
    }
}
