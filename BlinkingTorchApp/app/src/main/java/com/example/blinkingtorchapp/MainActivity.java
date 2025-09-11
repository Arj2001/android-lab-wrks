package com.example.blinkingtorchapp;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ToggleButton toggleButton;
    CameraManager cameraManager;
    TextView timesText;
    TextView delayText;
    String cameraId;
    Handler handler = new Handler();



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
        cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            throw new RuntimeException();
        }
        toggleButton = findViewById(R.id.toggleButton);

        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            toggleBlinkingTorch(isChecked);
        });
    }

    private void toggleBlinkingTorch(boolean isChecked) {
        if(isChecked){
                blinkTorch();
        }else{
            handler.removeCallbacks(null);
        }
    }
    private void blinkTorch(){
        timesText = findViewById(R.id.timesText);
        delayText = findViewById(R.id.delayText);
        final int times = Integer.parseInt(timesText.getText().toString());
        final int delay = Integer.parseInt(delayText.getText().toString());
        for (int i = 0; i < times; i++) {
            int finalI = i;
            handler.postDelayed(()->{
                try{
                    boolean state = finalI % 2 == 0;
                    cameraManager.setTorchMode(cameraId, state);
                } catch (Exception e) {
                    Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
                    throw new RuntimeException(e);
                }
            }, (long) delay * i);

        }
        toggleButton.setChecked(false);
    }
}