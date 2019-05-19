package com.example.project_practice.Folder;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.project_practice.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class Folder_Fragment extends Fragment {
    String transName;
    private static final String KEY_TRANS = "trans";


    public Folder_Fragment() {
        // Required empty public constructor
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
        return view;
    }

}
