package com.example.project_practice.FolderList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project_practice.R;

import java.util.ArrayList;

/**
 * 布局卡片集合
 */
public class Folder_RecyclerList_Adapter extends RecyclerView.Adapter<Folder_RecyclerList_Adapter.ViewHolder> {
    ArrayList<Folder_Recycler_item> items; //null并没有请求内存空间，所以下面的card_items不能null，因为它要add。但是items是在构造函数中传过来。
    Context context;

    public interface OnClickListener {
        void onCardClick(ImageView imageView);
    }

    private OnClickListener listener;

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    public Folder_RecyclerList_Adapter(ArrayList<Folder_Recycler_item> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item_folder, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
/*
        recyclerView = view.findViewById(R.id.card_item_folder_RecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);*/


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Folder_Recycler_item item = items.get(i);
        viewHolder.textView.setText(item.getTitle());
        viewHolder.imageView.setImageResource(item.getImg_id());

        //布局卡片内的RecyclerView
        //这是Card里的RecyclerView,理论上之后应该通过viewHolder的int[]来获取名字
        //所有Item子项的东西，！！不要不要不要不要！！，放置为成员变量
        ArrayList<Card_Recycler_item> card_items = new ArrayList<>();

        Card_Recycler_item item1 = new Card_Recycler_item("伊利亚特");
        Card_Recycler_item item2 = new Card_Recycler_item("奥德修斯");
        Card_Recycler_item item3 = new Card_Recycler_item("埃涅阿斯");
        Card_Recycler_item item4 = new Card_Recycler_item("阿斯卡尼俄斯");
        card_items.add(item1);
        card_items.add(item2);
        card_items.add(item3);
        card_items.add(item4);

        Card_Recycler_adapter adapter;
        adapter = new Card_Recycler_adapter(card_items);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        viewHolder.recyclerView.setLayoutManager(linearLayoutManager);
        viewHolder.recyclerView.setAdapter(adapter);
        viewHolder.recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

        ItemTouchHelper.Callback callback = new Card_Recycler_Callback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(viewHolder.recyclerView);

        //设置点击事件
        if (listener != null) {
            viewHolder.cardView.setOnClickListener(v -> listener.onCardClick(viewHolder.imageView));
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView textView;
        ImageView imageView;
        RecyclerView recyclerView;
        int[] articles_id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_item_folder_card);
            textView = itemView.findViewById(R.id.card_item_folder_text);
            imageView = itemView.findViewById(R.id.card_item_folder_img);
            recyclerView = itemView.findViewById(R.id.card_item_folder_RecyclerView);
        }
    }


    private void initCardItems() {

    }
}
