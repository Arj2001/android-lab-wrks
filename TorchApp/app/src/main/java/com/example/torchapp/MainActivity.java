package com.example.torchapp;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button flashBtn;
    ToggleButton toggleButton;
    private boolean isFlashON = false;
    String cameraId;
    CameraManager cameraManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.flash), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            Toast.makeText(getApplicationContext(), "Cannot turn on flash", Toast.LENGTH_LONG).show();
            throw new RuntimeException(e);
        }
        flashBtn = findViewById(R.id.flashBtn);
        toggleButton = findViewById(R.id.toggleButton);

        flashBtn.setOnClickListener(v->{
            toggleTorch();

        });
    }

    private void toggleTorch() {
        try{
            cameraManager.setTorchMode(cameraId, !isFlashON);
            isFlashON = !isFlashON;
            Toast.makeText(getApplicationContext(), "Flash is "+isFlashON, Toast.LENGTH_SHORT).show();
        }catch (CameraAccessException e){
            Toast.makeText(getApplicationContext(), "Cannot turn on flash", Toast.LENGTH_LONG).show();
            throw new RuntimeException(e);
        }
    }
}