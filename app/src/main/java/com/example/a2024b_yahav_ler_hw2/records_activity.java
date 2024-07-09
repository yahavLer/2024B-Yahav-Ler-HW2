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
import androidx.fragment.app.FragmentManager;
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

        gameManager gameManager = new gameManager(this);
        ArrayList<ScoreRecord> scores = gameManager.getScores();

        Bundle bundle = new Bundle();
        bundle.putSerializable("scores", scores);

        Fragment_List fragmentList = new Fragment_List();
        fragmentList.setArguments(bundle);

        Fragment_Map fragmentMap = new Fragment_Map();
        fragmentMap.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_LAY_top, fragmentList);
        fragmentTransaction.replace(R.id.main_LAY_bottom, fragmentMap);
        fragmentTransaction.commit();
    }
}