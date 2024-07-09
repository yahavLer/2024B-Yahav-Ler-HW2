package com.example.a2024b_yahav_ler_hw2;
import android.Manifest;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class records_activity extends AppCompatActivity implements OnMapReadyCallback {
    private FrameLayout main_LAY_top, main_LAY_bottom;
    private ScoreManager scoreManager;
    private Button button;

    private LinearLayout[] rows;
    private TextView[] scores;
    private TextView[] names;
    private final int FINE_PREMISSION_CODE = 1;
    private GoogleMap myMap;
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private Map<String, LatLng> playerLocations = new HashMap<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.records_view);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getLastLocation();
        } else {
            Toast.makeText(this, "Location permissions not granted", Toast.LENGTH_SHORT).show();
        }

        scoreManager = new ScoreManager(this);
        initBoard();
        displayHighScores();
        initBtnBack();
    }

    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;

                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    mapFragment.getMapAsync(records_activity.this);
                }
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        myMap = googleMap;
        if (currentLocation != null) {
            LatLng currentLocationLatLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
            myMap.addMarker(new MarkerOptions().position(currentLocationLatLng).title("My location"));
            float zoomLevel = 15.0f; // 1 smallest zoom level to 21 largest
            myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocationLatLng, zoomLevel));
        }
    }

    private void initBoard() {
        rows = new LinearLayout[]{
                findViewById(R.id.row1), findViewById(R.id.row2), findViewById(R.id.row3),
                findViewById(R.id.row4), findViewById(R.id.row5), findViewById(R.id.row6),
                findViewById(R.id.row7), findViewById(R.id.row8), findViewById(R.id.row9),
                findViewById(R.id.row10)};
        scores = new TextView[]{
                findViewById(R.id.score1), findViewById(R.id.score2), findViewById(R.id.score3),
                findViewById(R.id.score4), findViewById(R.id.score5), findViewById(R.id.score6),
                findViewById(R.id.score7), findViewById(R.id.score8), findViewById(R.id.score9),
                findViewById(R.id.score10)};
        names = new TextView[]{
                findViewById(R.id.playerName1), findViewById(R.id.playerName2), findViewById(R.id.playerName3),
                findViewById(R.id.playerName4), findViewById(R.id.playerName5), findViewById(R.id.playerName6),
                findViewById(R.id.playerName7), findViewById(R.id.playerName8), findViewById(R.id.playerName9),
                findViewById(R.id.playerName10)};
    }

    private void displayHighScores() {
        List<ScoreRecord> highScores = scoreManager.getRecords();
        for (int i = 0; i < highScores.size(); i++) {
            rows[i].setVisibility(View.VISIBLE);
            ScoreRecord record = highScores.get(i);
            names[i].setText(record.getPlayerName());
            scores[i].setText(String.valueOf(record.getScore()));

            LatLng playerLatLng = new LatLng(record.getLatitude(), record.getLongitude());
            playerLocations.put(record.getPlayerName(), playerLatLng);

            rows[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPlayerLocationOnMap(record.getPlayerName());
                }
            });
        }
        for (int i = highScores.size(); i < rows.length; i++) {
            rows[i].setVisibility(View.GONE);
        }
    }

    private void showPlayerLocationOnMap(String playerName) {
        LatLng playerLatLng = playerLocations.get(playerName);
        if (playerLatLng != null) {
            myMap.clear();
            myMap.addMarker(new MarkerOptions().position(playerLatLng).title(playerName + "'s location"));
            myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(playerLatLng, 15.0f));
        }
    }

    private void initBtnBack() {
        button = findViewById(R.id.btn_back);
        button.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        });
    }


}
