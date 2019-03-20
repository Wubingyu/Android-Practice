package com.example.diarylist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemContext_activity extends AppCompatActivity {
    TextView title_view,context_view;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemcontext);

        title_view = (TextView) findViewById(R.id.item_context_title);
        context_view = (TextView) findViewById(R.id.item_context_context);
        imageView = (ImageView) findViewById(R.id.item_context_img);

        int img_id = getIntent().getIntExtra("img_id", R.drawable.a20);
        String title = getIntent().getStringExtra("title");
        String context = getIntent().getStringExtra("context");

        imageView.setImageResource(img_id);
        context_view.setText(context);
        title_view.setText(title);
    }
}
