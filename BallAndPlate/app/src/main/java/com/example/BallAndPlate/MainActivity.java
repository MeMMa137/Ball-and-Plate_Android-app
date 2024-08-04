package com.example.javaapplication;

import static java.lang.Math.PI;
import static java.lang.Math.atan2;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorEventListener2;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, SensorEventListener {

    private Button incButton, resetButton, startButton;
    private TextView textView;
    private int n=0;

    //Gestione sensori
    private SensorManager sensorManager;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        incButton = findViewById(R.id.incButton);
        resetButton = findViewById(R.id.resetButton);
        startButton = findViewById(R.id.startButton);
        textView = findViewById(R.id.textView);
        incButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);
        startButton.setOnClickListener(this);
        textView.setText("I'm flexing");

        sensorManager = (SensorManager) getSystemService(this.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.incButton)
            n++;
        if(v.getId()==R.id.resetButton)
            n=0;
        incButton.setText(n+"");
        if(v.getId() == R.id.startButton) {
            Intent intent = new Intent(this, BallAndPlate.class);
            startActivity(intent);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        String s = "";
        s+="x: "+((int)(event.values[0]*100))/100.0+", ";
        s+="y: "+((int)(event.values[1]*100))/100.0+", ";
        s+="z: "+((int)(event.values[2]*100))/100.0;

        double theta = atan2(event.values[0],event.values[1]);
        double thetaDeg = theta*180/PI;
        textView.setText(s+" -> theta: "+((int)(thetaDeg*100))/100.0);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}