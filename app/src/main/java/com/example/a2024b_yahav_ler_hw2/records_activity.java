package com.example.a2024b_yahav_ler_hw2;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Collections;

public class records_activity extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 100;
    private FrameLayout main_LAY_top, main_LAY_bottom;
    private Fragment_List fragment_list;
    private Fragment_Map fragment_map;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.records_view);

        main_LAY_top = findViewById(R.id.main_LAY_top);
        main_LAY_bottom = findViewById(R.id.main_LAY_bottom);

        fragment_list = new Fragment_List();
        fragment_map = new Fragment_Map();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_LAY_top, fragment_list);
        transaction.replace(R.id.main_LAY_bottom, fragment_map);
        transaction.commit();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            loadScoresAndLocations();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadScoresAndLocations();
            } else {
                // Handle permission denial appropriately
            }
        }
    }

    private void loadScoresAndLocations() {
        SharedPreferences sharedPreferences = getSharedPreferences("game_data", MODE_PRIVATE);
        ArrayList<String> scoresList = new ArrayList<>();
        ArrayList<Double> latList = new ArrayList<>();
        ArrayList<Double> lonList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            String score = sharedPreferences.getString("score_" + i, null);
            if (score != null) {
                scoresList.add(score);
                latList.add(Double.longBitsToDouble(sharedPreferences.getLong("lat_" + i, 0)));
                lonList.add(Double.longBitsToDouble(sharedPreferences.getLong("lon_" + i, 0)));
            }
        }

        Collections.sort(scoresList, Collections.reverseOrder());
        StringBuilder scoresBuilder = new StringBuilder();
        for (String score : scoresList) {
            scoresBuilder.append(score).append("\n");
        }

        fragment_list.setScores(scoresBuilder.toString());

        for (int i = 0; i < latList.size(); i++) {
            fragment_map.addPendingLocation(latList.get(i), lonList.get(i));
        }
    }
}
