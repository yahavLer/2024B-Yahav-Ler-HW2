package com.example.a2024b_yahav_ler_hw2;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity  {

    private boolean useSensors;
    private gameManager game;
//    private SoundPlayer soundPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_view);
        useSensors = getIntent().getBooleanExtra("useSensors", false);
//        MyBackgroundMusic.init(this);
//        MyBackgroundMusic.getInstance().setResourceId(R.raw.gameloop);
        game = new gameManager(this, this, useSensors);
        game.findViews();
        game.initializeHorses();
        if (useSensors) {
            game.ButtonUnVisibility();
        } else {
            game.ButtonVisibility();
        }
//        makeSound();

        game.startGame(useSensors);
//        soundPlayer = new SoundPlayer(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (game.getMoveDetector() != null) {
            game.getMoveDetector().start();
        }
//        MyBackgroundMusic.getInstance().playMusic();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (game.getMoveDetector() != null) {
            game.getMoveDetector().stop();
        }
//        MyBackgroundMusic.getInstance().pauseMusic();
    }
//    private void makeSound() {
//        soundPlayer.playSound(R.raw.gameloop);
//    }
}
