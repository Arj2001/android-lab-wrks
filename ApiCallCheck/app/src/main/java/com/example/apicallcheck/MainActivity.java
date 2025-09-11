package com.example.apicallcheck;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
//    https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
    private static String key = "8503d1f92999e62fb7e126593c13bb0e";
    Button btn;
    TextView txtView;
    TextView locationTV;
    URL url;
    URIBu


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

        btn = findViewById(R.id.get);
        txtView = findViewById(R.id.textView);
        locationTV = findViewById(R.id.editLocation);
        btn.setOnClickListener(v -> {

        });
    }
}