package com.example.material_design;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

/**
 * 怎么划分出来之后，就不能滑动了？也没有正确显示fragment的内容，但是依然是三个tab？为啥
 * 因为。。我在新的layout XML中忘记设置LinearLayout的ori了，这也说明一旦调用setupWithViewPager之后，tabLayout的标签就靠adapter了
 */
public class tablayout_Activity extends AppCompatActivity {
    private static final String TAG = "tablayout_Activity";

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout_);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        /**
         * 对tablayout的使用
         */
//        TabLayout.Tab tab = tabLayout.newTab();
//        tab.setText("第一个");
//        tabLayout.addTab(tab);
        //下面是优雅的写法
        //*****使用CustomView的时候，前面的这些text和icon就都不见了。因为理论上他们都可以放在自定义view中 不是吗
        tabLayout.addTab(tabLayout.newTab().setText("first").setIcon(R.mipmap.ic_launcher_round).setCustomView(R.layout.tabcustomview));
        //*****这个setContentDescription没有显示出来
        tabLayout.addTab(tabLayout.newTab().setText("second").setIcon(R.mipmap.ic_launcher).setContentDescription("good night"));
        //setOnTabSelectedListener方法被废弃，使用addOnTabSelectedListener()
        tabLayout.addOnTabSelectedListener(new android.support.design.widget.TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(android.support.design.widget.TabLayout.Tab tab) {
                Log.d(TAG, "onTabSelected: " + tab.getText());
            }

            @Override
            public void onTabUnselected(android.support.design.widget.TabLayout.Tab tab) {
                Log.d(TAG, "onTabUnselected: " + tab.getText());
            }

            @Override
            public void onTabReselected(android.support.design.widget.TabLayout.Tab tab) {
                Log.d(TAG, "onTabReselected: " + tab.getText());
            }
        });

        /**
         * 下面是TabLayout和ViewPager相关联的部分
         * ？？？问题，怎么标签不见了，log的显示也变成了onTabSelected: null。原来是adapter中的getPageTitle是用的重写父类的方法，忘记设置了
         * 问题是：我的tab 的自定义的icon们怎么也不见了！看来用上adapter之后，全部的部分都被他接管了，然后title就不看上面的设置了啊。那我还需要设置addtab吗？ 试试看哦。
         *  好的 不需要了 笑死。tab的数量现在完全看adapter中的getCount
         *
         */
//        String[] tabTitleArray = {"first", "second"};
        //getSupportFragmentManager 一定要AppCompatActivity才有
        //和tabLayout Fragment一起用的时候，用FragmentPagerAdapter，需要三个方法，分别设置各个title，分页的数量、分页的fragment
        //但是字体、颜色等东西，还是看tabLayout的设置
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
