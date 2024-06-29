package com.example.a2024b_yahav_ler_hw2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import java.util.Random;

public class senssor_activity extends AppCompatActivity {
    private ImageView[][] zoo_animals;
    private ImageView[] zoo_live;
    private TextView numScore;
    private GameManager gameManager;
    private int amountRow = 9, amountColl = 5;
//    private MoveDetector moveDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.senssor_view);
        findViews();
        initializeHorses();
//        initMoveDetector();
        gameManager = new GameManager(this, zoo_animals, zoo_live, amountRow, amountColl);
        gameManager.initMoveDetector();
        gameManager.startGame();
    }

    private void initializeHorses() {
        Random random = new Random();
        for (int i = 0; i < zoo_animals.length-1; i++) {
            for (int j = 0; j < zoo_animals[i].length; j++) {
                if (i == 0) {
                    zoo_animals[i][j].setVisibility(random.nextBoolean() ? View.VISIBLE : View.INVISIBLE);
                } else {
                    zoo_animals[i][j].setVisibility(View.INVISIBLE);
                }
            }
        }

        for (int i = 1; i < zoo_animals.length-1; i++) {
            for (int j = 0; j < zoo_animals[i].length; j++) {
                if (zoo_animals[i - 1][j].getVisibility() == View.VISIBLE) {
                    zoo_animals[i][j].setVisibility(View.INVISIBLE);
                } else if (random.nextBoolean()) {
                    zoo_animals[i][j].setVisibility(View.VISIBLE);
                }
            }
        }
    }

//    private void initMoveDetector() {
//        moveDetector = new MoveDetector(this,
//                new MoveCallback() {
//                    @Override
//                    public void moveX() {
//                        if (moveDetector.getMoveCountX() > 0) {
//                            gameManager.moveFarmerRight();
//                        } else {
//                            gameManager.moveFarmerLeft();
//                        }
//                    }
//
//                    @Override
//                    public void moveY() {
//                        gameManager.setSpeed(moveDetector.getMoveCountY());
//                    }
//                });
//    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        moveDetector.stop();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        moveDetector.start();
//    }

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
        zoo_live = new AppCompatImageView[]{
                findViewById(R.id.live1),
                findViewById(R.id.live2),
                findViewById(R.id.live3),
        };
        numScore = findViewById(R.id.num_score);
    }
}
