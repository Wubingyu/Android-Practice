package com.example.picture_canvas;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.picture_canvas.CropPic.CropPicture;
import com.example.picture_canvas.getPicFromGallery.getPicActivity;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.sql.RowId;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Button button_select_pic;
    private Button button_toCropPic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        button_select_pic.setOnClickListener(v -> toActivity(getPicActivity.class));
        button_toCropPic.setOnClickListener(v -> toActivity(CropPicture.class));

    }

    private void init() {
        button_select_pic = (Button) findViewById(R.id.choose_pic);
        button_toCropPic = findViewById(R.id.Button_toCropPicActivity);
    }

    private void toActivity(Class c) {
        Intent intent = new Intent(MainActivity.this, c);
        startActivity(intent);
    }
}
