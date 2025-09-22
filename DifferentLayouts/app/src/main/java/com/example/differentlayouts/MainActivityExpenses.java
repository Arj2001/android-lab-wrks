package com.example.differentlayouts;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivityExpenses extends AppCompatActivity {


    DBConnection db = new DBConnection(this);
    Button add;
    TextView amtView;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_expenses);

        add = findViewById(R.id.addBtn);
        amtView = findViewById(R.id.amt);
        linearLayout = findViewById(R.id.linearLayout);

        add.setOnClickListener(v->{
            int amount = Integer.parseInt(amtView.getText().toString());
            db.insert(amount);
            displayData();
        });
        displayData();

    }

    void displayData(){
        linearLayout.removeAllViews();
        Cursor allItems = db.viewAll();
        while(allItems.moveToNext()){
            TextView textView = new TextView(this);
            textView.setText(allItems.getString(1));
            linearLayout.addView(textView);
        }
        TextView textView = new TextView(this);
        textView.setText("Total is: "+db.viewTotal());
        linearLayout.addView(textView);
        allItems.close();
    }

}