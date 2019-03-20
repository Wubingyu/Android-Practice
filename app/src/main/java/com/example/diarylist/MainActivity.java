package com.example.diarylist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button_toRecyclerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_toRecyclerList = (Button) findViewById(R.id.toRecyclerList);
        button_toRecyclerList.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, RecyclerView_activity.class);
            startActivity(intent);
        });
    }
}
