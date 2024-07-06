package com.example.a2024b_yahav_ler_hw2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnPlayButton;
    Button btnPlaySensor;
    Button btnRecords;
    private SoundPlayer soundPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        soundPlayer = new SoundPlayer(this);
        findViews();
        makeSound();
        btnPlaySensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("useSensors", true);
                startActivity(intent);
            }
        });

        btnPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("useSensors",false);
                startActivity(intent);
            }
        });
        btnRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, records_activity.class);
                startActivity(intent);
            }
        });
    }
    private void makeSound() {
        soundPlayer.playSound(R.raw.gameloop);
    }
    private void findViews() {
        btnPlaySensor = findViewById(R.id.btn_senssor);
        btnPlayButton = findViewById(R.id.btn_Button);
        btnRecords = findViewById(R.id.btn_records);
    }

    @Override
    protected void onResume() {
        super.onResume();
        soundPlayer.playSound(R.raw.gameloop); // Play sound on resume
    }

    @Override
    protected void onPause() {
        super.onPause();
        soundPlayer.stopSound(); // Stop sound on pause
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPlayer.release(); // Release resources on destroy
    }
}
