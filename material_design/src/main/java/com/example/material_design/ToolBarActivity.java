package com.example.material_design;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

public class ToolBarActivity extends AppCompatActivity {
    private static final String TAG = "ToolBarActivity";
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar);

        toolbar = findViewById(R.id.toolbar);


        toolbar.setNavigationOnClickListener(v -> finish());

        //添加溢出菜单
        // app:showAsAction 这个是在菜单的xml中设置的，还记得那个action吗？就是它了！
        toolbar.inflateMenu(R.menu.setting_menu);
        toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.item_setting:
                    Log.d(TAG, "onCreate: click item_setting");
                    break;
            }
            //事件分发机制
            return false;
        });
    }
}
