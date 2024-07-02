package com.example.a2024b_yahav_ler_hw2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textview.MaterialTextView;

public class Fragment_Map extends Fragment {

    private MaterialTextView map_LBL_location;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        MaterialTextView mapLBLLocation = view.findViewById(R.id.map_LBL_location);

        // Set your list of locations here
        mapLBLLocation.setText("Location 1\nLocation 2\nLocation 3");

        return view;
    }

    public void setLocation(double lat, double lon) {
        map_LBL_location.setText(lat + ", " + lon);
    }

}
