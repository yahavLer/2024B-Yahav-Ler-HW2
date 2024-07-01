package com.example.a2024b_yahav_ler_hw2;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Random;

public class gameManager extends AppCompatActivity{
    private AppCompatImageButton zoo_left;
    private AppCompatImageButton zoo_right;
    private ImageView[][] zoo_animals;
    private ImageView[] zoo_live;
    private int farmerPosCol;
    private int farmerPosRow;
    private boolean isGameOver = false;
    private int amountRow, amountColl;
    private int delay = 1000;
    private int numLives = 3;
    private final Handler handler = new Handler();
    private Runnable runnable;
    private MoveDetector moveDetector;
    private Context context;
    private AppCompatActivity  activity;
    private TextView numScore;
    private boolean gameSensors;
    public gameManager() {
        super();
    }

    public gameManager(Context context, AppCompatActivity gameActivity, boolean gameSensors) {
        this.context = context;
        this.activity = gameActivity;
        this.gameSensors = gameSensors;
    }



    private void initButton() {
        zoo_right.setOnClickListener(v -> moveFarmerRight());
        zoo_left.setOnClickListener(v -> moveFarmerLeft());
    }

    public void startGame(boolean useSensors) {
        if (useSensors) {
            initMoveDetector();
        } else {
            initButton();
        }
        this.runnable = new Runnable() {
            public void run() {
                if (!isGameOver) {
                    moveHorse();
                } else {
                    return;
                }
                handler.postDelayed(runnable, delay);
            }
        };
        handler.postDelayed(runnable, delay);
    }

    public void checkLives() {
        if (numLives == 0 && !isGameOver) {
            isGameOver = true;
            lose();
        } else {
            checkPlace();
        }
    }

    public void lose() {
        zoo_left.setEnabled(false);
        zoo_right.setEnabled(false);
        openAdvertisementDialog();
    }

    public void openAdvertisementDialog() {
        new MaterialAlertDialogBuilder(context).setTitle("No lives")
                .setMessage("You lose, Do you want to play again?")
                .setPositiveButton("Yes", (dialog, which) -> continueGame())
                .setNegativeButton("No", (dialog, which) -> gameDone())
                .show();
    }

    public void continueGame() {
        numLives = 3;
        updateLive();
        isGameOver = false;
        zoo_left.setEnabled(true);
        zoo_right.setEnabled(true);
//        delay = 1000;
//        for (ImageView[] zoo_animal : zoo_animals) {
//            for (ImageView imageView : zoo_animal) {
//                imageView.setVisibility(View.INVISIBLE);
//            }
//        }
//        startGame(gameSensors);
        start();
    }

    public void gameDone() {
        stopGame();
        Toast.makeText(context, "You lose", Toast.LENGTH_SHORT).show();
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
        Vibrator vibrator = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);        if (vibrator != null) {
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

    public void stopGame() {
        handler.removeCallbacks(runnable);
    }

    private void start() {
        handler.postDelayed(runnable, delay);
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

    public void moveFarmerRight() {
        if (farmerPosCol < amountColl - 1) {
            zoo_animals[farmerPosRow][farmerPosCol].setVisibility(View.INVISIBLE);
            farmerPosCol++;
            zoo_animals[farmerPosRow][farmerPosCol].setVisibility(View.VISIBLE);
//            checkPlace();
        }
    }

    public void moveFarmerLeft() {
        if (farmerPosCol > 0) {
            zoo_animals[farmerPosRow][farmerPosCol].setVisibility(View.INVISIBLE);
            farmerPosCol--;
            zoo_animals[farmerPosRow][farmerPosCol].setVisibility(View.VISIBLE);
//            checkPlace();
        }
    }

    public void setSpeed(int yTilt) {
        if (yTilt > 6) {
            delay = 500;  // Increase speed
        } else if (yTilt < -6) {
            delay = 1500; // Decrease speed
        } else {
            delay = 1000; // Normal speed
        }
    }


//    private void generateHorse() {
//        int col = new Random().nextInt(amountColl);
//        zoo_animals[0][col].setVisibility(View.VISIBLE);
//    }

    public void findViews() {
        this.zoo_left = activity.findViewById(R.id.zoo_left);
        this.zoo_right = activity.findViewById(R.id.zoo_right);
        this.numScore = activity.findViewById(R.id.num_score);
        this.zoo_animals = new AppCompatImageView[][]{
                {activity.findViewById(R.id.horse01), activity.findViewById(R.id.horse02), activity.findViewById(R.id.horse03), activity.findViewById(R.id.horse04), activity.findViewById(R.id.horse05)},
                {activity.findViewById(R.id.horse06), activity.findViewById(R.id.horse07), activity.findViewById(R.id.horse08), activity.findViewById(R.id.horse09), activity.findViewById(R.id.horse10)},
                {activity.findViewById(R.id.horse11), activity.findViewById(R.id.horse12), activity.findViewById(R.id.horse13), activity.findViewById(R.id.horse14), activity.findViewById(R.id.horse15)},
                {activity.findViewById(R.id.horse16), activity.findViewById(R.id.horse17), activity.findViewById(R.id.horse18), activity.findViewById(R.id.horse19), activity.findViewById(R.id.horse20)},
                {activity.findViewById(R.id.horse21), activity.findViewById(R.id.horse22), activity.findViewById(R.id.horse23), activity.findViewById(R.id.horse24), activity.findViewById(R.id.horse25)},
                {activity.findViewById(R.id.horse26), activity.findViewById(R.id.horse27), activity.findViewById(R.id.horse28), activity.findViewById(R.id.horse29), activity.findViewById(R.id.horse30)},
                {activity.findViewById(R.id.horse31), activity.findViewById(R.id.horse32), activity.findViewById(R.id.horse33), activity.findViewById(R.id.horse34), activity.findViewById(R.id.horse35)},
                {activity.findViewById(R.id.horse36), activity.findViewById(R.id.horse37), activity.findViewById(R.id.horse38), activity.findViewById(R.id.horse39), activity.findViewById(R.id.horse40)},
                {activity.findViewById(R.id.horse41), activity.findViewById(R.id.horse42), activity.findViewById(R.id.horse43), activity.findViewById(R.id.horse44), activity.findViewById(R.id.horse45)},
                {activity.findViewById(R.id.farmer1), activity.findViewById(R.id.farmer2), activity.findViewById(R.id.farmer3), activity.findViewById(R.id.farmer4), activity.findViewById(R.id.farmer5)}
        };
        this.zoo_live = new AppCompatImageView[]{
                activity.findViewById(R.id.live1),
                activity.findViewById(R.id.live2),
                activity.findViewById(R.id.live3),
        };
        this.amountRow = zoo_animals.length;
        this.amountColl = zoo_animals[0].length;
        this.farmerPosRow = amountRow - 1;
        this.farmerPosCol = amountColl / 2;
    }

    public void ButtonUnVisibility() {
        zoo_left.setVisibility(View.GONE);
        zoo_right.setVisibility(View.GONE);
//        initMoveDetector();
    }

    public void ButtonVisibility() {
        zoo_left.setVisibility(View.VISIBLE);
        zoo_right.setVisibility(View.VISIBLE);
//        initButton();
    }

    public void initializeHorses() {
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

//    public void initializeHorses() {
//        for (int i = 0; i < amountRow - 1; i++) {
//            for (int j = 0; j < amountColl; j++) {
//                zoo_animals[i][j].setVisibility(View.INVISIBLE);
//            }
//        }
//        zoo_animals[farmerPosRow][farmerPosCol].setVisibility(View.VISIBLE);
//    }

    private void initMoveDetector() {
        moveDetector = new MoveDetector(context, new MoveCallback() {
            @Override
            public void moveX() {
                if (moveDetector.getMoveCountX() > 0) {
                    moveFarmerRight();
                } else {
                    moveFarmerLeft();
                }
            }
            @Override
            public void moveY() {
                setSpeed(moveDetector.getMoveCountY());
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (moveDetector != null) {
            moveDetector.stop();
        }
        stopGame();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (moveDetector != null) {
            moveDetector.stop();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (moveDetector != null) {
            moveDetector.start();
        }
    }
}