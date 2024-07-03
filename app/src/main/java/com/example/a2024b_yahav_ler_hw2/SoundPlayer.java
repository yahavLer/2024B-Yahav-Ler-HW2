//package com.example.a2024b_yahav_ler_hw2;
//
//import android.content.Context;
//import android.media.MediaPlayer;
//
//public class SoundPlayer {
//    private MediaPlayer mediaPlayer;
//    private Context context;
//
//    public SoundPlayer(Context context) {
//        this.context = context.getApplicationContext(); // Ensure this is a valid context
//    }
//
//    public void playSound(int resId) {
//        if (mediaPlayer != null) {
//            mediaPlayer.release();
//        }
//        mediaPlayer = MediaPlayer.create(context, resId);
//        mediaPlayer.start();
//    }
//
//    public void stopSound() {
//        if (mediaPlayer != null) {
//            mediaPlayer.stop();
//            mediaPlayer.release();
//            mediaPlayer = null;
//        }
//    }
//}
