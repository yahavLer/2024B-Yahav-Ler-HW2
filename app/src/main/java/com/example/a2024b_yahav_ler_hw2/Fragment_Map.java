package com.example.a2024b_yahav_ler_hw2;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.a2024b_yahav_ler_hw2.ScoreRecord;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Fragment_Map extends Fragment implements OnMapReadyCallback {

    private ArrayList<ScoreRecord> scores;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            scores = (ArrayList<ScoreRecord>) getArguments().getSerializable("scores");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        for (ScoreRecord score : scores) {
            LatLng location = new LatLng(score.getLatitude(), score.getLongitude());
            googleMap.addMarker(new MarkerOptions().position(location).title(score.getPlayerName()));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        }
    }
}
