package com.example.differentlayouts;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivityColor extends AppCompatActivity {

    Button btn;
    TextView ranTxt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.colorBtn);
        ranTxt1 = findViewById(R.id.randTxt1);

        btn.setOnClickListener(v -> {
            Random rand = new Random();
            int color = Color.argb(255, rand.nextInt(256),
                    rand.nextInt(256),
                    rand.nextInt(256));
            ranTxt1.setTextColor(color);
        });
    }
}