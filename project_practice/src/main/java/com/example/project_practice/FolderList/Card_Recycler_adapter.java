package com.example.project_practice.FolderList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.project_practice.R;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 卡片里的列表
 * 这个列表的item项只有一个标题
 */
public class Card_Recycler_adapter extends RecyclerView.Adapter<Card_Recycler_adapter.ViewHolder> implements ItemTouchHelperAdapter {
    ArrayList<Card_Recycler_item> items = new ArrayList<>();


    public Card_Recycler_adapter(ArrayList<Card_Recycler_item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Card_Recycler_item item = items.get(i);
        viewHolder.textView.setText(item.getTitle());

    }

    @Override
    public int getItemCount() {
        return items.size() >= 5 ? 5 : items.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        //1.数据交换 2.刷新
        Collections.swap(items, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.card_item_text);
        }
    }
}
