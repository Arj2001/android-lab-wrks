package com.example.labexamall;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Crud extends AppCompatActivity {

    DataBaseConnection db = new DataBaseConnection(this);
    TextView name, salary, display;
    Button displayBtn, submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crud);

        name = findViewById(R.id.name);
        salary = findViewById(R.id.salary);
        display = findViewById(R.id.display);
        submit = findViewById(R.id.submit);
        displayBtn = findViewById(R.id.displayBtn);

        submit.setOnClickListener(v->{
            db.insert(name.getText().toString(), salary.getText().toString());
        });
        displayBtn.setOnClickListener(v->{
            display.setText(db.display());
        });
    }
}