package com.example.a2024b_yahav_ler_hw2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import java.util.Map;

public class records_activity extends AppCompatActivity {
    private Fragment_List fragmentList;
    private Fragment_Map fragmentMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.records_view);

        // Add List Records Fragment to main_LAY_top
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fragmentList = new Fragment_List();
        transaction.replace(R.id.main_LAY_top, fragmentList);
        transaction.commit();

        // Add Map Fragment to main_LAY_bottom
        FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
        fragmentMap = new Fragment_Map();
        transaction2.replace(R.id.main_LAY_bottom, fragmentMap);
        transaction2.commit();

        loadScores();
    }

    private void loadScores() {
        SharedPreferences sharedPreferences = getSharedPreferences("GameScores", Context.MODE_PRIVATE);
        Map<String, ?> allEntries = sharedPreferences.getAll();
        StringBuilder scores = new StringBuilder();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            scores.append(entry.getValue().toString()).append("\n");
        }
        if (fragmentList != null) {
            fragmentList.setScores(scores.toString());
        }
    }

    private CallBack_List meshulash = new CallBack_List() {
        @Override
        public void showLocationInMap(String user) {
            fragmentMap.setLocation(32.4, 34.5);
        }
    };
}
