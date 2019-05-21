package com.example.project_practice.Explore;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project_practice.R;
import com.example.project_practice.utils.CardItemTouchHelperCallback;
import com.example.project_practice.utils.CardLayoutManager;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExploreFragment extends Fragment {
    ArrayList<Explore_list_item> items;
    private static final String KEY_EXPLORE = "explore_items";

    RecyclerView recyclerView;
    Explore_list_adapter adapter;


    public ExploreFragment() {
        // Required empty public constructor
    }

    public static ExploreFragment newInstance(ArrayList<Explore_list_item> items) {
        ExploreFragment fragment = new ExploreFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(KEY_EXPLORE, items);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.items = getArguments().getParcelableArrayList(KEY_EXPLORE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        recyclerView = view.findViewById(R.id.ExploreFragment_RecyclerView);
        adapter = new Explore_list_adapter(items);
        CardItemTouchHelperCallback callback = new CardItemTouchHelperCallback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);

        RecyclerView.LayoutManager layoutManager = new CardLayoutManager(recyclerView, touchHelper);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        touchHelper.attachToRecyclerView(recyclerView);

        return view;
    }

}
