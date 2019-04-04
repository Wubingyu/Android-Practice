package com.example.material_design;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Button button_Snack_TextInput;
    Button button_tabLayout;
    Button button_ToolBar;
    Button button_AppBar;
    Button button_CardView;
    Button button_ViewPagerCards;
    Button button_DeignSupportNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        rootLayout = findViewById(R.id.rootLayout);

        /**
         * 初始化控件
         */
        button_Snack_TextInput = findViewById(R.id.jump_Snack_TextInput);
        button_tabLayout = findViewById(R.id.jump_tabLayout);
        button_ToolBar = findViewById(R.id.jump_ToolBar);
        button_AppBar = findViewById(R.id.jump_AppBar);
        button_CardView = findViewById(R.id.jump_CardView);
        button_ViewPagerCards = findViewById(R.id.jump_ViewPagerCards);
        button_DeignSupportNew = findViewById(R.id.jump_DeignSupportNew);

        button_Snack_TextInput.setOnClickListener(v -> navigateTo(Snackbar_TextinputLayout.class));
        button_tabLayout.setOnClickListener(v -> navigateTo(tablayout_Activity.class));
        button_ToolBar.setOnClickListener(v -> navigateTo(ToolBarActivity.class));
        button_AppBar.setOnClickListener(v -> navigateTo(AppBar.class));
        button_CardView.setOnClickListener(v -> navigateTo(CardViewActivity.class));
        button_ViewPagerCards.setOnClickListener(v -> navigateTo(ViewpagerCards.class));
        button_DeignSupportNew.setOnClickListener(v->navigateTo(DeignSupportNew.class));

    }

    void navigateTo(Class<?> destination) {
        Intent intent = new Intent(MainActivity.this, destination);
        startActivity(intent);
    }

}
