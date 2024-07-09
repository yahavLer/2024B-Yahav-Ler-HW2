package com.example.a2024b_yahav_ler_hw2;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ScoreManager {
    private static final String PREFS_NAME = "HighScores";
    private static final String SCORES_KEY = "Scores";
    private SharedPreferences preferences;
    private Gson gson;

    public ScoreManager(Context context) {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void addRecord(String name, int score, double latitude, double longitude) {
        List<ScoreRecord> records = getRecords();
        records.add(new ScoreRecord(name, score, latitude, longitude));
        records.sort((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()));
        if (records.size() > 10) {
            records.remove(records.size() - 1);
        }
        saveRecords(records);
    }

    public List<ScoreRecord> getRecords() {
        String json = preferences.getString(SCORES_KEY, "");
        Type type = new TypeToken<List<ScoreRecord>>() {}.getType();
        List<ScoreRecord> players = gson.fromJson(json, type);
        return players == null ? new ArrayList<>() : players;    }


    private void saveRecords(List<ScoreRecord> players) {
        String json = gson.toJson(players);
        preferences.edit().putString(SCORES_KEY, json).apply();
    }

}
