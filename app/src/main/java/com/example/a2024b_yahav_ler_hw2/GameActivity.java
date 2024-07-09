package com.example.a2024b_yahav_ler_hw2;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;
import android.Manifest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class GameActivity extends AppCompatActivity  {

    private boolean useSensors;
    private GameManager game;
    private MyBackgroundMusic backgroundMusic;
    private SoundPlayer soundPlayer;
    private Location location;
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_view);
        soundPlayer = new SoundPlayer(this);
        useSensors = getIntent().getBooleanExtra("useSensors", false);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        game = new GameManager(GameActivity.this, GameActivity.this, useSensors);
        game.findViews();
        game.initializeHorses();
        makeSoundBack();
        if (useSensors) {
            game.ButtonUnVisibility();
        } else {
            game.ButtonVisibility();
        }
        backgroundMusic = MyBackgroundMusic.getInstance();
        getLocationAndStartGame();
    }

    private void getLocationAndStartGame() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            return;
        }
        Task<Location> locationTask = fusedLocationClient.getLastLocation();
        locationTask.addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location loc) {
                if (loc != null) {
                    location = loc;
                    game.setCurrentLocation(location);
                } else {
                    Toast.makeText(GameActivity.this, "Unable to get location", Toast.LENGTH_SHORT).show();
                }
            }
        });
        game.startGame(useSensors);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (game != null && game.getMoveDetector() != null) {
            game.getMoveDetector().start();
        }
        if (backgroundMusic != null) {
            backgroundMusic.playMusic();
        }
        if (soundPlayer != null) {
            soundPlayer.playSound(R.raw.gameloop, true);
        }
    }

    private void makeSoundBack() {
        soundPlayer.playSound(R.raw.gameloop, true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (game != null && game.getMoveDetector() != null) {
            game.getMoveDetector().stop();
        }
        if (backgroundMusic != null) {
            backgroundMusic.pauseMusic();
        }
        if (soundPlayer != null) {
            soundPlayer.stopSound(); // Stop sound on pause
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (game != null && game.getMoveDetector() != null) {
            game.getMoveDetector().stop();
        }
        if (soundPlayer != null) {
            soundPlayer.release(); // Release resources on destroy
        }
        if (game != null) {
            game.stopGame();
        }
    }
}
