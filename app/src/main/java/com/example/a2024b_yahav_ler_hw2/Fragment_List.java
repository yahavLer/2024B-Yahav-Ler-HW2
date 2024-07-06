package com.example.a2024b_yahav_ler_hw2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textview.MaterialTextView;

public class Fragment_List extends Fragment {

    private MaterialTextView list_LBL_user;
    private String scores = "";

    public void setScores(String scores) {
        this.scores = scores;
        if (list_LBL_user != null) {
            list_LBL_user.setText(scores);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_records, container, false);
        list_LBL_user = view.findViewById(R.id.list_LBL_user);

        if (!scores.isEmpty()) {
            list_LBL_user.setText(scores);
        }
        return view;
    }
}
