package com.example.differentlayouts;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivityListView extends AppCompatActivity {

    ListView listView;
    String[] contacts = {
            "Amelia Chen",
            "Ben Carter",
            "Chloe Davis",
            "David Evans",
            "Emily Foster",
            "Frank Green",
            "Grace Hall",
            "Henry Irwin",
            "Isla Jones",
            "Jack King"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_listview);

        listView = findViewById(R.id.listContact);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contacts);
        listView.setAdapter(adapter);
    }

}