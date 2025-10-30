package com.example.studentcrud;

import android.content.Intent;
import android.database.Cursor;
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

    int id;
    boolean editable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_student);

        nameIn = findViewById(R.id.nameIn);
        clsIn = findViewById(R.id.clsIn);
        submitBtn = findViewById(R.id.addSubmitBtn);

        id = getIntent().getIntExtra("ID",-1);
        editable = id != -1;

        submitBtn.setOnClickListener(v -> {
            if(editable)
                db.updateStudent(id,nameIn.getText().toString(), clsIn.getText().toString());
            else
                db.insertStudent(nameIn.getText().toString(), clsIn.getText().toString());
            finish();
        });

        if(editable){
            Cursor val = db.getStudent(id);
            val.moveToFirst();
            nameIn.setText(val.getString(1));
            clsIn.setText(val.getString(2));
        }
    }
}