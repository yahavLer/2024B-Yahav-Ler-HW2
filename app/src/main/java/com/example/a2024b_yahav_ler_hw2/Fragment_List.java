package com.example.a2024b_yahav_ler_hw2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.textview.MaterialTextView;

public class Fragment_List extends Fragment {

    private MaterialTextView list_LBL_user;

    private CallBack_List callBackList;

    public void setCallBackList(CallBack_List callBackList) {
        this.callBackList = callBackList;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_records, container, false);
        MaterialTextView listLBLUser = view.findViewById(R.id.list_LBL_user);

        // Set your list of records here
        listLBLUser.setText("Record 1\nRecord 2\nRecord 3");

        return view;
    }

    public void setUserName(String name) {
        list_LBL_user.setText(name);
    }

}