package com.example.project_practice.FolderList;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.project_practice.Folder.Folder_Fragment;
import com.example.project_practice.MainActivity;
import com.example.project_practice.R;
import com.example.project_practice.utils.SpaceItemDecoration;

import java.util.ArrayList;

/**
 *
 * A simple {@link Fragment} subclass.
 */
public class FolderListFragment extends Fragment{
    private static final String TAG = "FolderListFragment";

    RecyclerView recyclerView;
    ArrayList<Folder_Recycler_item> items = new ArrayList<>();
    Folder_RecyclerList_Adapter adapter;

    public FolderListFragment() {
        //        if (items.size() == 0) {
        initItems();
        if (getActivity() == null) { //上面这行代码是不能写的，因为在使用构造函数的时候，根本没有getActivity()可以获得
            Log.d(TAG, "FolderListFragment: ");
        }
//        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        adapter = new Folder_RecyclerList_Adapter(items, getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        ArrayList<Folder_Recycler_item> items = new ArrayList<>();

        View view = inflater.inflate(R.layout.fragment_folder_list, container, false);

        recyclerView = view.findViewById(R.id.FolderListFragment_RecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SpaceItemDecoration(30));
        Log.d(TAG, "---------- items 大小为：" + items.size());



        //设置点击事件
        adapter.setListener((position, imageView) -> {
            Folder_Fragment fragment = Folder_Fragment.newInstance("transition" + position);
            ((MainActivity) getActivity()).showFragmentWithTransition(FolderListFragment.this, fragment, imageView, "transition" + position);
        });
        return view;
    }

    // 初始化Folder列表，里面全是Card
    private void initItems() {
        Folder_Recycler_item item1 = new Folder_Recycler_item(R.drawable.a, "角斗场", null);
        Folder_Recycler_item item2 = new Folder_Recycler_item(R.drawable.c, "Roma", null);

        items.add(item1);
        items.add(item2);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }


}
