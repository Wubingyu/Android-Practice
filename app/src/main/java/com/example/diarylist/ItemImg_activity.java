package com.example.diarylist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ItemImg_activity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemimg);

        imageView = (ImageView) findViewById(R.id.item_img);
        //这个后面的int是干啥的？defaultValue，你懂的
        imageView.setImageResource(getIntent().getIntExtra("img_id", R.drawable.a20));

    }
}
