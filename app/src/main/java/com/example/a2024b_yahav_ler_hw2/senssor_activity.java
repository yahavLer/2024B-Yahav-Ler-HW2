package com.example.a2024b_yahav_ler_hw2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

public class senssor_activity extends AppCompatActivity {
    private ImageView[][] zoo_animals;
    private ImageView[] zoo_live;
    private int farmerPosCol=1;
    private int farmerPosRow=5;
    private TextView numScore;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.senssor_view);
    }

    private void findViews() {
        zoo_animals = new AppCompatImageView[][]{
                {findViewById(R.id.horse01), findViewById(R.id.horse02), findViewById(R.id.horse03), findViewById(R.id.horse04), findViewById(R.id.horse05)},
                {findViewById(R.id.horse06), findViewById(R.id.horse07), findViewById(R.id.horse08), findViewById(R.id.horse09), findViewById(R.id.horse10)},
                {findViewById(R.id.horse11), findViewById(R.id.horse12), findViewById(R.id.horse13), findViewById(R.id.horse14), findViewById(R.id.horse15)},
                {findViewById(R.id.horse16), findViewById(R.id.horse17), findViewById(R.id.horse18), findViewById(R.id.horse19), findViewById(R.id.horse20)},
                {findViewById(R.id.horse21), findViewById(R.id.horse22), findViewById(R.id.horse23), findViewById(R.id.horse24), findViewById(R.id.horse25)},
                {findViewById(R.id.horse26), findViewById(R.id.horse27), findViewById(R.id.horse28), findViewById(R.id.horse29), findViewById(R.id.horse30)},
                {findViewById(R.id.horse31), findViewById(R.id.horse32), findViewById(R.id.horse33), findViewById(R.id.horse34), findViewById(R.id.horse35)},
                {findViewById(R.id.horse36), findViewById(R.id.horse37), findViewById(R.id.horse38), findViewById(R.id.horse39), findViewById(R.id.horse40)},
                {findViewById(R.id.horse41), findViewById(R.id.horse42), findViewById(R.id.horse43), findViewById(R.id.horse44), findViewById(R.id.horse45)},
                {findViewById(R.id.farmer1), findViewById(R.id.farmer2), findViewById(R.id.farmer3), findViewById(R.id.farmer4), findViewById(R.id.farmer5)}
        };
        zoo_live = new AppCompatImageView[] {
                findViewById(R.id.live1),
                findViewById(R.id.live2),
                findViewById(R.id.live3),
        };
        numScore = findViewById(R.id.num_score);
    }

}
