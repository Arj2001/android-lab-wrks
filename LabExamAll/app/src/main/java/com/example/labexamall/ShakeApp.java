package com.example.labexamall;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;

public class ShakeApp extends AppCompatActivity implements SensorEventListener {
    ConstraintLayout layout;
    SensorManager sensorManager;
    Sensor sensorAccel;
    float lastX,lastY, lastZ;
    Random ran = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shake_app);
        layout = findViewById(R.id.shakeMain);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorAccel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensorAccel, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
//        Toast.makeText(this, "shake changed", Toast.LENGTH_SHORT).show();
            if(lastX != event.values[0] || lastY != event.values[1] || lastZ != event.values[2]){
                lastX = event.values[0];
                lastY = event.values[1];
                lastZ = event.values[2];
                layout.setBackgroundColor(Color.rgb(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256)));
            }
    }
}