package com.example.a2024b_yahav_ler_hw2;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity  {

    private boolean useSensors;
    private gameManager game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_view);
        useSensors = getIntent().getBooleanExtra("useSensors", false);

        game = new gameManager(this, this);
        game.findViews();
        game.initializeHorses();
        if (useSensors) {
            game.ButtonUnVisibility();
        } else {
            game.ButtonVisibility();
        }
        game.startGame(useSensors);
    }
}
