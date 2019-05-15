package com.example.project_practice.myFolder;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.project_practice.R;

import java.util.ArrayList;

/**
 *
 * A simple {@link Fragment} subclass.
 */
public class FolderListFragment extends Fragment{
    ArrayList<Card_Recycler_item> items = new ArrayList<>();
    Card_Recycler_adapter adapter;

    public
        // Inflate the layout for this fragment
        FolderListFragment() {
            // Required empty public constructor
        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_folder_list, container, false);

        FrameLayout frameLayout = view.findViewById(R.id.FolderListFragment_frameLayout);
        LayoutInflater.from(getContext()).inflate(R.layout.card_item_folder, frameLayout, true);

        //这是Card里的RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.card_item_folder_RecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        initItems();
        adapter = new Card_Recycler_adapter(items);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        ItemTouchHelper.Callback callback = new Card_Recycler_Callback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);

        return view;
    }

    private void initItems() {
        Card_Recycler_item item1 = new Card_Recycler_item("伊利亚特");
        Card_Recycler_item item2 = new Card_Recycler_item("奥德修斯");
        Card_Recycler_item item3 = new Card_Recycler_item("埃涅阿斯");
        Card_Recycler_item item4 = new Card_Recycler_item("阿斯卡尼俄斯");
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
    }

}
