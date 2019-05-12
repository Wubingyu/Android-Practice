package com.example.project_practice.myFolder;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.project_practice.R;

import java.util.ArrayList;

public class FolderListActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ArrayList<BlankFragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_list);

        //卡片集布局，如何实现？卡片里文章list的长度不超过5个，但是也有可能少于5个。
        //包含响应按钮，添加一篇新文章、改变封面图像、删除、隐藏、展开文章列表 之类的
        //https://www.jianshu.com/p/ec781c3ccfb8

        initFragments();
        initView();


    }

    private void initFragments() {
        BlankFragment blankFragment1 = BlankFragment.newInstance("第一个", "1st");
        BlankFragment blankFragment2 = BlankFragment.newInstance("第二个", "2nd");
        BlankFragment blankFragment3 = BlankFragment.newInstance("第三个", "3rd");
        BlankFragment blankFragment4 = BlankFragment.newInstance("第四个", "4th");
        fragments.add(blankFragment1);
        fragments.add(blankFragment2);
        fragments.add(blankFragment3);
        fragments.add(blankFragment4);
    }

    private void initView() {
        bottomNavigationView = findViewById(R.id.BottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> onTabItemSelected(item.getItemId()));

        onTabItemSelected(R.id.navigation_bottom_folder_list);
    }

    private boolean onTabItemSelected(int id) {
        Fragment fragment = null;
        switch (id) {
            case R.id.navigation_bottom_folder_list:
                fragment = fragments.get(0);
                break;
            case R.id.navigation_bottom_message:
                fragment = fragments.get(1);
                break;
            case R.id.navigation_bottom_explore:
                fragment = fragments.get(2);
                break;
            case R.id.navigation_bottom_info:
                fragment = fragments.get(3);
                break;
        }
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder,fragment).commit();
            return true;
        }
        return false;
    }
}

