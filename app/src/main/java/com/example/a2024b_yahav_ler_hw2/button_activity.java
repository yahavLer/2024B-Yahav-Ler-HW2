package com.example.a2024b_yahav_ler_hw2;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;

public class button_activity extends AppCompatActivity {
    private AppCompatImageButton zoo_left;
    private AppCompatImageButton zoo_right;
    private ImageView[][] zoo_animals;
    private ImageView[] zoo_live;
    private GameManager gameManager;
    private  int amountRow=5, amountColl=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_view);
        findViews();

        gameManager = new GameManager(this, zoo_left, zoo_right, zoo_animals, zoo_live,   amountRow,  amountColl);
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
        zoo_live = new AppCompatImageView[]{
                findViewById(R.id.live1),
                findViewById(R.id.live2),
                findViewById(R.id.live3),
        };
    }
}
