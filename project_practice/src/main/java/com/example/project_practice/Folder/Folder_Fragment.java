package com.example.project_practice.Folder;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project_practice.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class Folder_Fragment extends Fragment {


    public Folder_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_folder_, container, false);
    }

}
