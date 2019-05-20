package com.example.project_practice.Folder;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.project_practice.R;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class Folder_Fragment extends Fragment {
    ArrayList<Article_Recycler_item> items = new ArrayList<>();
    String transName;
    RecyclerView recyclerView;
    Article_Recycler_adapter adapter;
    private static final String KEY_TRANS = "trans";


    public Folder_Fragment() {
        // Required empty public constructor
        initItems();
    }

    public static Folder_Fragment newInstance(String transitionName) {
        Folder_Fragment fragment = new Folder_Fragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TRANS, transitionName);
        fragment.setArguments(bundle);
        return fragment;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        transName = getArguments().getString(KEY_TRANS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_folder_, container, false);
        ImageView imageView = view.findViewById(R.id.Folder_Fragment_img);
        imageView.setTransitionName(transName);


        //创建自己的RecyclerView，并设置流式布局
        recyclerView = view.findViewById(R.id.Folder_Fragment_RecyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Article_Recycler_adapter(items);
        recyclerView.setAdapter(adapter);

        adapter.setListener(v -> Toast.makeText(getActivity(), "click", Toast.LENGTH_SHORT));

        return view;
    }

    private void initItems() {
        Article_Recycler_item item1 = new Article_Recycler_item("罗马之美", R.drawable.d);
        Article_Recycler_item item2 = new Article_Recycler_item("旅行日记", R.drawable.e);
        Article_Recycler_item item3 = new Article_Recycler_item("竞技场", R.drawable.f);
        Article_Recycler_item item4 = new Article_Recycler_item("万神殿", R.drawable.g);
        Article_Recycler_item item5 = new Article_Recycler_item("拉丁族", R.drawable.h);

        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);
    }

}
