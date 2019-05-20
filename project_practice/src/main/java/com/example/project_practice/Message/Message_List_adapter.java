package com.example.project_practice.Message;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project_practice.R;

import java.util.ArrayList;

public class Message_List_adapter extends RecyclerView.Adapter<Message_List_adapter.ViewHolder> {
    ArrayList<Message_List_item> items;

    public Message_List_adapter(ArrayList<Message_List_item> items) {
        this.items = items;
    }

    public interface onListener {
        void clickItem();
    }

    private onListener listener;

    public void setListener(onListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.message_list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Message_List_item item = items.get(i);
        viewHolder.headView.setImageResource(item.getImg_id());
        viewHolder.nameView.setText(item.getName());
        viewHolder.LastTimeView.setText(item.getLastTime());
        viewHolder.LastChatView.setText(item.getLast_Message());

        if (listener != null) {
            viewHolder.itemView.setOnClickListener(v -> listener.clickItem());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView headView;
        TextView nameView, LastChatView, LastTimeView;
        View itemView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            headView = itemView.findViewById(R.id.MessageList_item_img);
            nameView = itemView.findViewById(R.id.MessageList_item_name);
            LastChatView = itemView.findViewById(R.id.MessageList_item_lastChat);
            LastTimeView = itemView.findViewById(R.id.MessageList_item_lastTime);
            this.itemView = itemView;
        }
    }
}
