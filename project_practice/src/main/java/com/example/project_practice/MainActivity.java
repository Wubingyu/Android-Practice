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
import com.example.project_practice.Message.MessageFragment;
import com.example.project_practice.Message.Message_List_item;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ArrayList<Fragment> fragments = new ArrayList<>();

    ArrayList<Message_List_item> message_list_items = new ArrayList<>();

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
        initMessageList();
        Fragment fragment_message = MessageFragment.newInstance(message_list_items);
        BlankFragment blankFragment3 = new BlankFragment();
//        Fragment test = new testFragment();
        Fragment test = testFragment.newInstance("this is testFragment newInstance");
        fragments.add(fragment1);
        fragments.add(fragment_message);
        fragments.add(blankFragment3);
//        fragments.add(blankFragment4);
        fragments.add(test);
    }

    private void initMessageList() {
        Message_List_item item1 = new Message_List_item("朋友一", "上一条消息", "两天前", 0, R.drawable.h);
        Message_List_item item2 = new Message_List_item("朋友二", "明天一起去吃东西", "3分钟前", 0, R.drawable.i);
        message_list_items.add(item1);
        message_list_items.add(item2);
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
        //想要根据左右，数量的方式实现滑动，就判断i是变大还是变小，i之间的距离是多少。
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
