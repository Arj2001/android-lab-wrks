package com.example.registerandlogin;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    public static final String EXTRA_USERNAME = "extra_username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView tvWelcome = findViewById(R.id.tv_welcome);
        String username = getIntent().getStringExtra(EXTRA_USERNAME);
        if (username == null) username = "user";
        tvWelcome.setText("Welcome, " + username);
    }
}
