package com.example.project_practice.Explore;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project_practice.R;
import com.example.project_practice.utils.CardSwipeAdapter;

import java.util.ArrayList;

public class Explore_list_adapter extends RecyclerView.Adapter<Explore_list_adapter.ViewHolder> implements CardSwipeAdapter {
    ArrayList<Explore_list_item> items;


    public interface onListener {
        void clickItem(int position);
    }

    onListener listener = null;

    public void setListener(onListener listener) {
        this.listener = listener;
    }

    public Explore_list_adapter(ArrayList<Explore_list_item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public Explore_list_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_explore, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Explore_list_adapter.ViewHolder viewHolder, int i) {
        Explore_list_item item = items.get(i);
        viewHolder.imageView.setImageResource(item.img_id);
        viewHolder.titleView.setText(item.title);
        viewHolder.briefView.setText(item.brief);

        if (listener != null) {
            viewHolder.itemView.setOnClickListener(v -> listener.clickItem(i));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleView, briefView;
        View itemView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ExploreItem_img);
            titleView = itemView.findViewById(R.id.ExploreItem_Title);
            briefView = itemView.findViewById(R.id.ExploreItem_brief);
            this.itemView = itemView;
        }
    }

    @Override
    public void onSwipe() {
        items.remove(0);
        notifyDataSetChanged();
    }
}
