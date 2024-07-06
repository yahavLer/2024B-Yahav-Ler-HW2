package com.example.a2024b_yahav_ler_hw2;

import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MyBackgroundMusic.init(this);
        MyBackgroundMusic.getInstance().setResourceId(R.raw.gameloop);
    }
}

