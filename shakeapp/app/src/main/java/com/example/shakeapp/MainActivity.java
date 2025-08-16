package com.example.shakeapp;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    Sensor sensorAccel;
    SensorManager sensorManager;
    ConstraintLayout rLayout;
    private float lastX, lastY, lastZ;
    private final int threshold = 500;
    private long lastTime;
    View view;
    Random ran = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rLayout = findViewById(R.id.main);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if(sensorManager!=null) sensorAccel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }

    @Override
    public void onResume(){
        super.onResume();
        if(sensorAccel!=null) sensorManager.registerListener(this,sensorAccel, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        long currentTime = System.currentTimeMillis();
        if(currentTime-lastTime > 100){
            long diffTime = currentTime - lastTime;
            lastTime = currentTime;
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            float speed = Math.abs(x+y+z-lastX-lastY-lastZ)/diffTime*10000;
            if(speed>threshold) changeBackgroundColor();
            lastX = x;
            lastY = y;
            lastZ = z;
        }
    }

    private void changeBackgroundColor() {
        int color = Color.rgb(ran.nextInt(256),ran.nextInt(256),ran.nextInt(256));
        rLayout.setBackgroundColor(color);
    }
}