package com.example.project_practice.myFolder;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.project_practice.R;
import com.example.project_practice.utils.SpaceItemDecoration;

import java.util.ArrayList;

/**
 *
 * A simple {@link Fragment} subclass.
 */
public class FolderListFragment extends Fragment{
    private static final String TAG = "FolderListFragment";
    //    ArrayList<Card_Recycler_item> items = new ArrayList<>();
//    Card_Recycler_adapter adapter;
    RecyclerView recyclerView;
    ArrayList<Folder_Recycler_item> items = new ArrayList<>();
    Folder_RecyclerList_Adapter adapter;
    public
        // Inflate the layout for this fragment
        FolderListFragment() {
            // Required empty public constructor
        }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        if (items.size() == 0) {
            initItems();
        }
        adapter = new Folder_RecyclerList_Adapter(items, getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");

        View view = inflater.inflate(R.layout.fragment_folder_list, container, false);

        //这两行是原来加载Card布局的，现在使用RecyclerView了
//        FrameLayout frameLayout = view.findViewById(R.id.FolderListFragment_frameLayout);
//        LayoutInflater.from(getContext()).inflate(R.layout.card_item_folder, frameLayout, true);

        //这是Card里的RecyclerView
        //注释掉，放在Folder_RecyclerList_Adapter中了
/*        RecyclerView recyclerView = view.findViewById(R.id.card_item_folder_RecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        initItems();
        adapter = new Card_Recycler_adapter(items);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        ItemTouchHelper.Callback callback = new Card_Recycler_Callback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);*/

        recyclerView = view.findViewById(R.id.FolderListFragment_RecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);


        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SpaceItemDecoration(30));
        Log.d(TAG, "onCreateView完毕 大小为：" + items.size());
        return view;
    }

    // 初始化Folder列表，里面全是Card
    private void initItems() {
        Folder_Recycler_item item1 = new Folder_Recycler_item(R.drawable.a, "角斗场", null);
        Folder_Recycler_item item2 = new Folder_Recycler_item(R.drawable.c, "Roma", null);

        items.add(item1);
        items.add(item2);
    }

/*    private void initItems() {
        Card_Recycler_item item1 = new Card_Recycler_item("伊利亚特");
        Card_Recycler_item item2 = new Card_Recycler_item("奥德修斯");
        Card_Recycler_item item3 = new Card_Recycler_item("埃涅阿斯");
        Card_Recycler_item item4 = new Card_Recycler_item("阿斯卡尼俄斯");
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
    }*/



}
