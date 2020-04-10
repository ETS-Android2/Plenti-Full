package com.example.plenti_full.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.plenti_full.R;


public class TutorialLayoutFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private int mParam1;


    public TutorialLayoutFragment() {
        // Required empty public constructor
    }

    public static TutorialLayoutFragment newInstance(int param1){
        TutorialLayoutFragment fragment = new TutorialLayoutFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tutorial_layout, container, false);
        if(mParam1 != 0){
            ImageView gearImage = view.findViewById(R.id.tutorialScreen);
            gearImage.setImageResource(mParam1);
        }



        return view;
    }

}
