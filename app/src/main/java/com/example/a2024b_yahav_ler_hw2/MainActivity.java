package com.example.a2024b_yahav_ler_hw2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button  btn_playButton;
    Button  btn_play_senssor;
    Button  btn_records;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        findViews();
        btn_playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, button_activity.class);
                startActivity(intent);
            }
        });
        btn_play_senssor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, senssor_activity.class);
                startActivity(intent);
            }
        });
        btn_records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, records_activity.class);
                startActivity(intent);
            }
        });
    }

    private void findViews() {
        btn_play_senssor = findViewById(R.id.btn_senssor);
        btn_playButton = findViewById(R.id.btn_Button);
        btn_records = findViewById(R.id.btn_records);
    }
}