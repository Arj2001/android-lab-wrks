package com.example.studentcrud;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddStudent extends AppCompatActivity {

    TextView nameIn, clsIn;
    DBConnection db = new DBConnection(this);
    Button submitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_student);

        nameIn = findViewById(R.id.nameIn);
        clsIn = findViewById(R.id.clsIn);
        submitBtn = findViewById(R.id.addSubmitBtn);

        submitBtn.setOnClickListener(v -> {
            db.insertStudent(nameIn.getText().toString(), clsIn.getText().toString());
            finish();
        });
    }
}