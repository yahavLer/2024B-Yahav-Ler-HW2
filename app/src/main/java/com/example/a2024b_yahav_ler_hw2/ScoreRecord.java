package com.example.a2024b_yahav_ler_hw2;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.Serializable;
import java.util.ArrayList;

public class ScoreRecord implements Serializable {
    private String playerName;
    private int score;
    private double latitude;
    private double longitude;

    public ScoreRecord(String playerName, int score, double latitude, double longitude) {
        this.playerName = playerName;
        this.score = score;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public static String toJson(ArrayList<ScoreRecord> scoreRecords) {
        JSONArray jsonArray = new JSONArray();
        for (ScoreRecord scoreRecord : scoreRecords) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("playerName", scoreRecord.getPlayerName());
                jsonObject.put("score", scoreRecord.getScore());
                jsonObject.put("latitude", scoreRecord.getLatitude());
                jsonObject.put("longitude", scoreRecord.getLongitude());
                jsonArray.put(jsonObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jsonArray.toString();
    }

    public static ArrayList<ScoreRecord> fromJson(String json) {
        ArrayList<ScoreRecord> scoreRecords = new ArrayList<>();
        if (json.isEmpty()) return scoreRecords;
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String playerName = jsonObject.getString("playerName");
                int score = jsonObject.getInt("score");
                double latitude = jsonObject.getDouble("latitude");
                double longitude = jsonObject.getDouble("longitude");
                scoreRecords.add(new ScoreRecord(playerName, score, latitude, longitude));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scoreRecords;
    }
}
