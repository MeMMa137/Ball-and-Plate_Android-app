package com.example.javaapplication;

import static java.lang.Math.PI;
import static java.lang.Math.atan2;
import static java.lang.Math.sin;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Debug;
import android.view.ViewGroup.LayoutParams;

import androidx.appcompat.app.AppCompatActivity;

import java.util.logging.Logger;

public class BallAndPlate extends AppCompatActivity implements SensorEventListener {
    private GameView gameView;
    private Engine engine;
    //Gestione sensori
    private SensorManager sensorManager;
    private Sensor sensor;

    private double t, scale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addContentView(gameView, params);

        engine = new Engine();
        scale = 100;
        t = System.currentTimeMillis();

        /*(new Thread(new Runnable() {
            @Override
            public void run() {
                float t=0;
                while(true) {
                    gameView.x = (float)(200*sin(t))+360;
                    //System.out.println(gameView.x);
                    gameView.invalidate();
                    t+=0.1;
                    try {
                        Thread.sleep(100);
                    }catch(Exception e){}
                }
            }
        })).start();
        System.out.println("Activity started");*/

        sensorManager = (SensorManager) getSystemService(this.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        String s = "";
        s+="x: "+((int)(event.values[0]*100))/100.0+", ";
        s+="y: "+((int)(event.values[1]*100))/100.0+", ";
        s+="z: "+((int)(event.values[2]*100))/100.0;

        double thetax = atan2(event.values[0],event.values[1]);
        double now = System.currentTimeMillis();
        double delta = now - t;
        engine.update(thetax, 0 ,delta/1000);
        t =  now;
        double thetaDeg = thetax*180/PI;
        //System.out.println(s+" -> theta: "+((int)(thetaDeg*100))/100.0);

        gameView.x = (int)(scale*engine.getX());
        gameView.invalidate();
        System.out.println(gameView.x+" -> theta: "+((int)(thetaDeg*100))/100.0);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

}