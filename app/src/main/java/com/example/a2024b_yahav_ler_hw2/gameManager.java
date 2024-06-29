package com.example.a2024b_yahav_ler_hw2;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Random;

class GameManager extends AppCompatActivity {
    private static final String TAG = "GameManager";
    private Context context;
    private AppCompatImageButton zoo_left;
    private AppCompatImageButton zoo_right;
    private ImageView[][] zoo_animals;
    private ImageView[] zoo_live;
    private int farmerPosCol = 1;
    private int farmerPosRow = 5;
    private boolean isGameOver = false;
    private int amountRow, amountColl;
    private int delay = 1000;
    private int numLives = 3;
    private final Handler handler = new Handler();
    private Runnable runnable;
    private MoveDetector moveDetector;


    public GameManager(Context context, AppCompatImageButton zoo_left, AppCompatImageButton zoo_right, ImageView[][] zoo_animals, ImageView[] zoo_live, int amountRow, int amountColl) {
        this.context = context;
        this.zoo_left = zoo_left;
        this.zoo_right = zoo_right;
        this.zoo_animals = zoo_animals;
        this.zoo_live = zoo_live;
        this.amountColl = amountColl;
        this.amountRow = amountRow;

        this.zoo_right.setOnClickListener(v -> moveFarmerRight());
        this.zoo_left.setOnClickListener(v -> moveFarmerLeft());

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
        startGame();
    }

    public GameManager(senssor_activity context, ImageView[][] zoo_animals, ImageView[] zoo_live, int amountRow, int amountColl) {
        this.context = context;
        this.zoo_animals = zoo_animals;
        this.zoo_live = zoo_live;
        this.amountColl = amountColl;
        this.amountRow = amountRow;
    //    initMoveDetector();

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
        //startGame();
    }

    public void startGame() {
        handler.postDelayed(runnable, delay);
    }
    private void gameDone() {
        stopGame();
        Toast.makeText(this, "You lose", Toast.LENGTH_SHORT).show();
        Log.d("pttt", "Game Done");
        zoo_left.setEnabled(false);
        zoo_right.setEnabled(false);
        finish();
    }
    public void stopGame() {
        handler.removeCallbacks(runnable);
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

    private void checkLives() {
        if (numLives == 0 && !isGameOver) {
            isGameOver = true;
            lose();
        } else {
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
                .setPositiveButton("Yes", (dialog, which) -> reset())
                .setNegativeButton("No", (dialog, which) -> gameDone())
                .show();
    }
    private void reset() {
        numLives = 3;
        isGameOver = false;
        zoo_left.setEnabled(true);
        zoo_right.setEnabled(true);
        delay = 1000;
        for (ImageView[] zoo_animal : zoo_animals) {
            for (ImageView imageView : zoo_animal) {
                imageView.setVisibility(View.INVISIBLE);
            }
        }
        startGame();
    }

    private void checkPlace() {
        if (zoo_animals[farmerPosRow][farmerPosCol].getVisibility() == View.VISIBLE) {
            numLives--;
            zoo_animals[farmerPosRow][farmerPosCol].setVisibility(View.INVISIBLE);
        }
    }

    public void moveFarmerRight() {
        if (farmerPosCol<amountColl-1) {
            zoo_animals[farmerPosRow][farmerPosCol].setVisibility(View.INVISIBLE);
            farmerPosCol += 1;
            zoo_animals[farmerPosRow][farmerPosCol].setVisibility(View.VISIBLE);
        }
    }

    public void moveFarmerLeft() {
        if (farmerPosCol>0) {
            zoo_animals[farmerPosRow][farmerPosCol].setVisibility(View.INVISIBLE);
            farmerPosCol -= 1;
            zoo_animals[farmerPosRow][farmerPosCol].setVisibility(View.VISIBLE);
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

    public void initMoveDetector() {
        moveDetector = new MoveDetector(this,
                new MoveCallback() {
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
    protected void onPause() {
        super.onPause();
        moveDetector.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        moveDetector.start();
    }
}
