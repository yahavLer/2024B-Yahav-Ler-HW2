package com.example.a2024b_yahav_ler_hw2;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Random;
import java.util.Timer;

public class button_activity extends AppCompatActivity {
    private Context context;

    private AppCompatImageButton zoo_left;
    private AppCompatImageButton zoo_right;
    private ImageView[][] zoo_animals;
    private ImageView zoo_farmer;
    private ImageView[] zoo_live;
    private int farmerPosCol=1;
    private int farmerPosRow=5;
    private boolean isGameOver = false;

    private int count = 0;
    private final int DELAY = 1000;

    private Timer timer;
    private int numLives=3;
    private final Handler handler = new Handler();

    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_view);
        findViews();
        zoo_right.setOnClickListener(v -> moveFarmerRight());
        zoo_left.setOnClickListener(v -> moveFarmerLeft());
        runnable = new Runnable() {
            public void run() {
                if (!isGameOver){
                    moveHorse();
                }else
                    return;
                handler.postDelayed(runnable, DELAY);
            }
        };
        start();
    }

    private void checkLives() {
        if (numLives == 0 && !isGameOver) {
            isGameOver = true;
            lose();
        }
        else {
            checkPlace();
        }
    }
    private void lose() {
        zoo_left.setEnabled(false);
        zoo_right.setEnabled(false);
        openAdvertisementDialog();
    }
    private void openAdvertisementDialog() {
        new MaterialAlertDialogBuilder(this).setTitle("No lives")
                .setMessage("You lose, Do you want to play again?")
                .setPositiveButton("Yes", (dialog, which) -> continueGame())
                .setNegativeButton("No", (dialog, which) -> gameDone())
                .show();
    }

    private void continueGame() {
        numLives=3;
        updateLive();
        isGameOver=false;
        zoo_left.setEnabled(true);
        zoo_right.setEnabled(true);
        start();
    }

    private void gameDone() {
        stop();
        Toast.makeText(this, "You lose", Toast.LENGTH_SHORT).show();
        Log.d("pttt", "Game Done");
        zoo_left.setEnabled(false);
        zoo_right.setEnabled(false);
        finish();
    }
    private void checkPlace() {
        if (zoo_animals[farmerPosRow-1][farmerPosCol].getVisibility() == View.VISIBLE) {
            numLives--;
            Log.d(TAG, "numLives: "+ numLives);
            updateLive();
            vibrate();
        }
    }

    private void vibrate(){
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                vibrator.vibrate(500);
            }
        }
    }

    private void moveHorse() {
        checkLives();
        Random random = new Random();
        int num;
        for (int i = zoo_animals.length - 3; i >= 0; i--) {
            for (int j = 0; j < zoo_animals[i].length; j++) {
                if (i==zoo_animals.length-3){
                    zoo_animals[i+1][j].setVisibility(View.INVISIBLE);
                }
                if (zoo_animals[i][j].getVisibility() == View.VISIBLE) {
                    zoo_animals[i + 1][j].setVisibility(View.VISIBLE);
                    zoo_animals[i][j].setVisibility(View.INVISIBLE);
                }
            }
            if (i==0){
                num= random.nextInt(3) ;
                zoo_animals[0][num].setVisibility(View.VISIBLE);
            }
        }
    }


    private void stop() {
        handler.removeCallbacks(runnable);
    }

    private void start() {
        handler.postDelayed(runnable, DELAY);
    }
    private void updateLive() {
        int amountLive=zoo_live.length;
        for (int i = 0; i < amountLive; i++) {
            zoo_live[i].setVisibility(View.VISIBLE);
        }
        if (numLives<3){
            int removeLive= zoo_live.length - numLives;
            for (int i = 0; i <removeLive ; i++) {
                zoo_live[(amountLive - i)-1].setVisibility(View.INVISIBLE);
            }
        }
    }

    private void moveFarmerRight() {
        if (farmerPosCol<2) {
            zoo_animals[farmerPosRow][farmerPosCol].setVisibility(View.INVISIBLE);
            farmerPosCol += 1;
            zoo_animals[farmerPosRow][farmerPosCol].setVisibility(View.VISIBLE);
        }
        //checkPlace();
    }

    private void moveFarmerLeft() {
        if (farmerPosCol>0) {
            zoo_animals[farmerPosRow][farmerPosCol].setVisibility(View.INVISIBLE);
            farmerPosCol -= 1;
            zoo_animals[farmerPosRow][farmerPosCol].setVisibility(View.VISIBLE);
        }
        //checkPlace();
    }

    private void findViews() {
        zoo_left = findViewById(R.id.zoo_left);
        zoo_right = findViewById(R.id.zoo_right);
        zoo_animals = new AppCompatImageView[][]{
                {findViewById(R.id.animal1), findViewById(R.id.animal2), findViewById(R.id.animal3)},
                {findViewById(R.id.animal4), findViewById(R.id.animal5), findViewById(R.id.animal6)},
                {findViewById(R.id.animal7), findViewById(R.id.animal8), findViewById(R.id.animal9)},
                {findViewById(R.id.animal10), findViewById(R.id.animal11), findViewById(R.id.animal12)},
                {findViewById(R.id.animal13), findViewById(R.id.animal14), findViewById(R.id.animal15)},
                {findViewById(R.id.farmer0), findViewById(R.id.farmer1), findViewById(R.id.farmer2)}
        };
        zoo_live = new AppCompatImageView[] {
                findViewById(R.id.live1),
                findViewById(R.id.live2),
                findViewById(R.id.live3),
        };
    }
}
