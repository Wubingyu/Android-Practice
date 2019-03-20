package com.example.diarylist;

import android.databinding.ObservableField;
import android.util.Log;

import com.example.diarylist.DataBinding.mvvmUtils;

public class RecyclerView_item {
    private static final String TAG = "RecyclerView_item";

    private ObservableField<String> title = new ObservableField<>();

    private ObservableField<String> context = new ObservableField<>();

    private ObservableField<Integer> img_id = new ObservableField<>();

    public RecyclerView_item() {
        title.set("");
        context.set("");
        img_id.set(R.drawable.a20);

        mvvmUtils.toObservable(title).subscribe(s -> Log.d(TAG, " title changed. Now the title is: " + title));
        mvvmUtils.toObservable(context).subscribe(s -> Log.d(TAG, "context changed. Now the context is: " + context));
    }

    public RecyclerView_item(String title, String context, int img_id) {
        this.title.set(title);
        this.context.set(context);
        this.img_id.set(img_id);

        mvvmUtils.toObservable(this.title).subscribe(s -> Log.d(TAG, " title changed. Now the title is: " + (String) title));
        mvvmUtils.toObservable(this.context).subscribe(s -> Log.d(TAG, "context changed. Now the context is: " + (String)context));
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getContext() {
        return context.get();
    }

    public void setContext(String context) {
        this.context.set(context);
    }

    public int  getImg_id() {
        return img_id.get();
    }

    public void setImg_id(int img_id) {
        this.img_id.set(img_id);
    }
}
