package com.example.material_design;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Snackbar_TextinputLayout extends AppCompatActivity {
    //TextInputLayout 还可以设置密码隐藏、输入的字符数量什么的。用处很多哦

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar__textinput);

        button = findViewById(R.id.makeSnackbar);

        /**
         * 对Snackbar的设置，它不需要在XML中定义，直接在代码中使用
         */
        button.setOnClickListener(v->{
            //第一个参数是个View，它会自动找到这个view最外层的布局来显示
            Snackbar.make(button, "Snackbar comes out", Snackbar.LENGTH_LONG)
                    .setAction("Action", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(
                                    Snackbar_TextinputLayout.this,
                                    "Toast comes out",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }).show();
        });
    }
}
