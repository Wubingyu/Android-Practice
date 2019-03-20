package com.example.diarylist;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.diarylist.databinding.ActivityNewItemcontextBinding;

import static com.example.diarylist.RecyclerView_activity.ACTIVITY_RESULT_CONTEXT;
import static com.example.diarylist.RecyclerView_activity.ACTIVITY_RESULT_TITLE;

public class New_ItemContext_activity extends AppCompatActivity {
    private static final String TAG = "New_ItemContext_activit";

    RecyclerView_item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);

        item = new RecyclerView_item();
        /**
         * 天秀，binding的方法是和XML的文件名有关的，也太秀了，所以在之前看的那个MVVM-DEMO中是如何通过抽象类进行全部绑定的呢？之后回去看看。
         */

//  使用databinding报错，也不知道是为什么？好像是使用inflate的方法不对？？？为啥啊。以后还是使用DataBindingUtil.setContentView(this, R.layout.activity_new__itemcontext);把
//        ActivityNewItemcontextBinding binding = ActivityNewItemcontextBinding.inflate(getLayoutInflater());
        //注意layout中是否用上了databinding。总而言之是完成绑定了
        ActivityNewItemcontextBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_new__itemcontext);
        binding.setItem(item);
//        setContentView(R.layout.activity_new__itemcontext);



    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed: ");
        //没有成功呢
        Intent intent = new Intent();
        intent.putExtra(ACTIVITY_RESULT_TITLE, item.getTitle());
        intent.putExtra(ACTIVITY_RESULT_CONTEXT, item.getContext());
        setResult(RESULT_OK, intent);
        //很明显，startActivityForResult这个方法必须要通过finish才能收到数据。
        finish(); //这个finish是会把整个活动结束了把？
    }
}
