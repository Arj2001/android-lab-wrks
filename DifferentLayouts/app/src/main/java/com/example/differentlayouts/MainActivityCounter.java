package com.example.differentlayouts;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityCounter extends AppCompatActivity {

    private int counter = 0;
    private TextView tvCounter;
    private Button btnIncrement, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_counter);

        tvCounter = findViewById(R.id.tvCounter);
        btnIncrement = findViewById(R.id.btnIncrement);
        btnReset = findViewById(R.id.btnReset);

        // Increment button
        btnIncrement.setOnClickListener(v -> {
            counter++;
            tvCounter.setText(String.valueOf(counter));
        });

        // Reset button
        btnReset.setOnClickListener(v -> {
            counter = 0;
            tvCounter.setText(String.valueOf(counter));
        });
    }
}
