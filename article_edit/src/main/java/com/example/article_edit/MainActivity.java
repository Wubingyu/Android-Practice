package com.example.article_edit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button toArticleEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toArticleEdit = (Button) findViewById(R.id.toArticleEdit);
        toArticleEdit.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, ArticleEdit_activity.class);
            startActivity(intent);
        });
    }
}
