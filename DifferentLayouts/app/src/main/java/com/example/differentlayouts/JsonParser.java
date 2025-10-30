package com.example.differentlayouts;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser extends AppCompatActivity {

    TextView in, out;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_json_parser);

        in = findViewById(R.id.input);
        out = findViewById(R.id.output);
        btn = findViewById(R.id.button8);

        btn.setOnClickListener(v -> {

            try {
                JSONObject rootObject = new JSONObject(in.getText().toString());

                JSONObject studentObject = rootObject.getJSONObject("student");
                String name = studentObject.getString("name");
                int age = studentObject.getInt("age");
                String course = studentObject.getString("course");

                JSONArray subjectsArray = rootObject.getJSONArray("subjects");

                StringBuilder subjectsList = new StringBuilder();
                for (int i = 0; i < subjectsArray.length(); i++) {
                    subjectsList.append("• ").append(subjectsArray.getString(i)).append("\n");
                }

                String result = "Student Details:\n"
                        + "Name: " + name + "\n"
                        + "Age: " + age + "\n"
                        + "Course: " + course + "\n\n"
                        + "Subjects:\n" + subjectsList;

                out.setText(result);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

        });


    }
}