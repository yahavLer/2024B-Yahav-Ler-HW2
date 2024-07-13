package com.example.a2024b_yahav_ler_hw2;



import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


public class MoveDetector {

    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEventListener sensorEventListener;

    private long timestamp = 0l;

    private MoveCallback moveCallback;
    private int tiltRightCount=0;
    private int tiltLeftCount=0;
    private int tiltBackwardCount=0;
    private int tiltForwardCount=0;

    public MoveDetector(Context context,MoveCallback moveCallback) {
        this.sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.moveCallback = moveCallback;
        initEventListener();
    }

    public int getTiltRightCount() {
        return tiltRightCount;
    }

    public int getTiltBackwardCount() {
        return tiltBackwardCount;
    }

    public int getTiltForwardCount() {
        return tiltForwardCount;
    }

    public int getTiltLeftCount() {
        return tiltLeftCount;
    }


    private void initEventListener() {
        this.sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];
                float y = event.values[1];
                calculateMove(x,y);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                // pass
            }
        };
    }

    private void calculateMove(float x, float y) {
        if (System.currentTimeMillis() - timestamp > 500) {
            timestamp = System.currentTimeMillis();

            if (x < -4.0) {
                tiltRightCount++;
                if (moveCallback != null) {
                    moveCallback.moveRight();
                }
            } else if (x > 4.0) {
                tiltLeftCount++;
                if (moveCallback != null) {
                    moveCallback.moveLeft();
                }
            }

//            if (y > 4.0) {
//                tiltBackwardCount++;
//                if (moveCallback != null) {
//                    moveCallback.moveBackward();
//                }
//            } else if (y < -4.0) {
//                tiltForwardCount++;
//                if (moveCallback != null) {
//                    moveCallback.moveForward();
//                }
//            }
        }
    }

    public void start(){
        sensorManager.registerListener(
                sensorEventListener,
                sensor,
                SensorManager.SENSOR_DELAY_NORMAL
        );
    }

    public void stop(){
        sensorManager.unregisterListener(
                sensorEventListener,
                sensor
        );
    }
}