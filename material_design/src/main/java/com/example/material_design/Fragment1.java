package com.example.material_design;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class Fragment1 extends CardFragment {
    CardView cardView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);
        cardView = view.findViewById(R.id.cardView1);
        cardView.setMaxCardElevation(24);

        return view;
    }

    @Override
    public CardView getCardView() {
        return cardView;
    }
}
