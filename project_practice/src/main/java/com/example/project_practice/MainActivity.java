package com.example.project_practice;

import android.os.Build;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.TransitionInflater;
import android.view.View;

import com.example.project_practice.FolderList.BlankFragment;
import com.example.project_practice.FolderList.FolderListFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        setupWindowAnimations();
        initFragments();
        initView();


    }

    private void initFragments() {
        FolderListFragment fragment1 = new FolderListFragment();
        BlankFragment blankFragment2 = new BlankFragment();
        BlankFragment blankFragment3 = new BlankFragment();
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
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(
                        R.anim.slide_right_in,
                        R.anim.slide_left_out,
                        R.anim.slide_left_in,
                        R.anim.slide_right_out)
//                    .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
//                    .addToBackStack(null)
                    .replace(R.id.fragment_placeholder, fragment).commit();
            return true;
        }
        return false;
    }

    /**
     * function to show the fragment
     *
     * @param current current visible fragment
     */
//    public void showFragmentWithTransition(Fragment current, Fragment newFragment, String tag, View sharedView, String sharedElementName) {
    public void showFragmentWithTransition(Fragment current, Fragment newFragment, View sharedView, String sharedElementName) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        // check if the fragment is in back stack
//        boolean fragmentPopped = fragmentManager.popBackStackImmediate(tag, 0);
//        if (fragmentPopped) {
//             fragment is pop from backStack
//        } else {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

//            current.setSharedElementReturnTransition(new ChangeBounds());
//            current.setExitTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.no_transition));
//
            newFragment.setSharedElementEnterTransition(new ChangeBounds());
//            newFragment.setEnterTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.no_transition));
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_placeholder, newFragment).addToBackStack(null)
                .addSharedElement(sharedView, sharedElementName).commit();
        }
    }
