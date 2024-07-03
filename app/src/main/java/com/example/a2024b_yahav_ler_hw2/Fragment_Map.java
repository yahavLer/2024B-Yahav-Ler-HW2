package com.example.a2024b_yahav_ler_hw2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Map extends Fragment implements OnMapReadyCallback {

    private MaterialTextView map_LBL_location;
    private MapView mapView;
    private GoogleMap googleMap;
    private List<LatLng> pendingLocations = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        map_LBL_location = view.findViewById(R.id.map_LBL_location);
        mapView = view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        // Add all pending locations to the map
        for (LatLng location : pendingLocations) {
            addMarkerToMap(location);
        }
        pendingLocations.clear();
    }

    public void addPendingLocation(double lat, double lon) {
        LatLng location = new LatLng(lat, lon);
        if (googleMap == null) {
            pendingLocations.add(location);
        } else {
            addMarkerToMap(location);
        }
    }

    private void addMarkerToMap(LatLng location) {
        if (googleMap != null) {
            map_LBL_location.setText(location.latitude + ", " + location.longitude);
            googleMap.addMarker(new MarkerOptions().position(location).title("Score Location"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
