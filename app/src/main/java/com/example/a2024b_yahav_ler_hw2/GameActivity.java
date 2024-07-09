package com.example.a2024b_yahav_ler_hw2;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity  {

    private boolean useSensors;
    private GameManager game;
    private MyBackgroundMusic backgroundMusic;
    private SoundPlayer soundPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_view);
        soundPlayer = new SoundPlayer(this);
        useSensors = getIntent().getBooleanExtra("useSensors", false);
        game = new GameManager(this, this, useSensors);
        game.findViews();
        game.initializeHorses();
        makeSoundBack();
        if (useSensors) {
            game.ButtonUnVisibility();
        } else {
            game.ButtonVisibility();
        }
        game.startGame(useSensors);
        backgroundMusic = MyBackgroundMusic.getInstance();
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (game.getMoveDetector() != null) {
            game.getMoveDetector().start();
        }
        if (backgroundMusic != null) {
            backgroundMusic.playMusic();
        }
        if(soundPlayer!=null){
            soundPlayer.playSound(R.raw.gameloop, true);
        }
    }
    private void makeSoundBack() {
        soundPlayer.playSound(R.raw.gameloop, true);
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (game.getMoveDetector() != null) {
            game.getMoveDetector().stop();
        }
        if (backgroundMusic != null) {
            backgroundMusic.pauseMusic();
        }
        if(soundPlayer!=null){
            soundPlayer.stopSound(); // Stop sound on pause
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (game.getMoveDetector() != null) {
            game.getMoveDetector().stop();
        }
        if(soundPlayer!=null){
            soundPlayer.release(); // Release resources on destroy
        }
        game.stopGame();
    }

}
