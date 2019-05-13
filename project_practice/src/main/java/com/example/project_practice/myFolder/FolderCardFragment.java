package com.example.project_practice.myFolder;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project_practice.R;

//用户的收藏
public class FolderCardFragment extends Fragment {


    public static FolderCardFragment newInstance() {
        FolderCardFragment fragment = new FolderCardFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_folder_card, container, false);
        return view;
    }
}
