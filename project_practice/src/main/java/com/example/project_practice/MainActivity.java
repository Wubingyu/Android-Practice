package com.example.project_practice;

import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.project_practice.myFolder.BlankFragment;
import com.example.project_practice.myFolder.FolderListActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button button_folderList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_folderList = findViewById(R.id.jump_FolderList);
        button_folderList.setOnClickListener(v -> jump(FolderListActivity.class));
    }

    private void jump(Class<?> activity) {
        Intent intent = new Intent(MainActivity.this, FolderListActivity.class);
        startActivity(intent);
    }
}
