package com.example.picture_canvas.CropPic;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.picture_canvas.R;
import com.yalantis.ucrop.UCrop;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 *  因为直接看uCrop的sample没有看懂，所以先重新回顾了如何取相册的图片，然后现在学习如何用uCrop裁剪图片
 */
public class CropPicture extends AppCompatActivity {
    private static final String TAG = "CropPicture";
    public static final int CHOOSE_PHOTO = 1;

    //看看是否bitmap = data.getParcelableExtra("data"); 就是拿到剪切后的照片
    Bitmap bitmap;

    //这个应该是指存储目标名？好像是这样，但是实际上在MT管理器中发现存储名字前面还有一串数字，这个数字是从哪里来的呢？
    private static final String SAMPLE_CROPPED_IMAGE_NAME = "SampleCropImage";

    Button button_getPic;
    ImageView showPic;
//    Button button_toShowBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_picture);

        bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.sakura);
        //UCrop的使用方法似乎是用uri的，所以放在R.drawable中的图片没啥子用
        button_getPic = findViewById(R.id.Crop_getPic);
        showPic = findViewById(R.id.Crop_imgShow);

        button_getPic.setOnClickListener(v -> openAlbum());


    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode){
                case CHOOSE_PHOTO:
                    Uri uri = data.getData();
                    //开始裁剪？
                    startCrop(uri);
                    break;
                case UCrop.REQUEST_CROP:
                    final Uri resultUri = UCrop.getOutput(data);

                    //成功取得bitmap这样之后就存bitmap到数据库就ok了！
                    try {
                        if(resultUri!=null) {
                            Bitmap bit = BitmapFactory.decodeStream(getContentResolver().openInputStream(resultUri));
                            showPic.setImageBitmap(bit);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

//                    String imagePath = getTruePath(resultUri);
//                    if (imagePath.isEmpty()) {
//                        Log.d(TAG, "cannot get the true imagePath");
//                    } else {
//                        displayImage(imagePath); // 根据图片路径显示图片
//                    }
            }
        }
    }


    /**
     * 介绍如何使用
     * @param uri
     */
    private void startCrop(@NonNull Uri uri) {
        String destinationFileName = SAMPLE_CROPPED_IMAGE_NAME;
        //保存格式为png格式
        destinationFileName += ".png";

        //使用构造者模式来创建 uCrop  ： UCrop.of(sourceUri, destinationUri)
        //而开始裁剪的方法是：start(context);
        UCrop uCrop = UCrop.of(uri, Uri.fromFile(new File(getCacheDir(), destinationFileName)));

        //这个方法它应该是用来设置裁剪的大小的。
//        uCrop = basisConfig(uCrop);
        uCrop = uCrop.withAspectRatio(1, 1);

        //裁剪界面和裁剪结构的设置
//        uCrop = advancedConfig(uCrop);
        UCrop.Options options = new UCrop.Options();
        options.setCompressionFormat(Bitmap.CompressFormat.PNG);
//        options.setHideBottomControls(true);  //是否隐藏操作栏
        uCrop.withOptions(options);

        //开始，然后在onActivityResult中处理
        uCrop.start(CropPicture.this);



        }

    private String getTruePath(Uri uri) {
        if (DocumentsContract.isDocumentUri(this, uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                return getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                return getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，则使用普通方式处理
            return getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径即可
            return uri.getPath();
        }

        return null;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            showPic.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }

    //内容提供器，取得图片。
    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }


}
