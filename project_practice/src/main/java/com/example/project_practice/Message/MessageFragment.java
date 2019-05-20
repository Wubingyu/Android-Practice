package com.example.project_practice.Message;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_practice.R;
import com.example.project_practice.utils.MessageItemDecoration;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {
    private static String KEY_MESSAGE = "message fragment arrayList items";
    ArrayList<Message_List_item> items;
    RecyclerView recyclerView;
    Message_List_adapter adapter;

    public MessageFragment() {
        // Required empty public constructor
    }

    public static MessageFragment newInstance(ArrayList<Message_List_item> items) {
        MessageFragment fragment = new MessageFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(KEY_MESSAGE, items);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        items = getArguments().getParcelableArrayList(KEY_MESSAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        recyclerView = view.findViewById(R.id.MessageFragment_RecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new Message_List_adapter(items);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new MessageItemDecoration());

        adapter.setListener(() -> Toast.makeText(getActivity(), "click", Toast.LENGTH_SHORT).show());

        return view;
    }

}
