package com.example.project_practice;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class testFragment extends Fragment {
    TextView textView;

    private static final String ARG_PARAM1 = "param1";
    private String mParam1;


    public testFragment() {
        // Required empty public constructor
    }

    public static testFragment newInstance(String s ) {
        testFragment fragment = new testFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, s);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        textView = view.findViewById(R.id.test_TextView);
        textView.setText(mParam1);

        return view;
    }
}
