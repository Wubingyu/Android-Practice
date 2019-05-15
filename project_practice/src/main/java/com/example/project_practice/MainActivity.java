package com.example.project_practice;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.project_practice.myFolder.BlankFragment;
import com.example.project_practice.myFolder.FolderListFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragments();
        initView();


    }

    private void initFragments() {
        FolderListFragment fragment1 = new FolderListFragment();
        BlankFragment blankFragment2 = BlankFragment.newInstance("第二个", "2nd");
        BlankFragment blankFragment3 = BlankFragment.newInstance("第三个", "3rd");
        BlankFragment blankFragment4 = BlankFragment.newInstance("第四个", "4th");
//        Fragment test = new testFragment();
        Fragment test = testFragment.newInstance("this is testFragment newInstance");
        fragments.add(fragment1);
        fragments.add(blankFragment2);
        fragments.add(blankFragment3);
//        fragments.add(blankFragment4);
        fragments.add(test);
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
