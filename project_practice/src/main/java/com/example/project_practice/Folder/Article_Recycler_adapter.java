package com.example.project_practice.Folder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project_practice.R;

import java.util.ArrayList;

public class Article_Recycler_adapter extends RecyclerView.Adapter<Article_Recycler_adapter.ViewHolder> {
    ArrayList<Article_Recycler_item> items;

    public interface onListener {
        void onViewClick(int position);
    }

    private onListener listener = null;

    public void setListener(onListener listener) {
        this.listener = listener;
    }

    public Article_Recycler_adapter(ArrayList<Article_Recycler_item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_article, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Article_Recycler_item item = items.get(i);
        viewHolder.textView.setText(item.title);
        viewHolder.imageView.setImageResource(item.getImg_id());
        if (listener != null) {
            viewHolder.itemView.setOnClickListener(v -> listener.onViewClick(i));
        }


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        View itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_list_article_img);
            textView = itemView.findViewById(R.id.item_list_article_text);
            this.itemView = itemView;
        }
    }
}
