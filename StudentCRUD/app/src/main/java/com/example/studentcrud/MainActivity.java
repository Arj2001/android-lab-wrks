package com.example.studentcrud;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button add, update, delete;
    TableLayout table;
    DBConnection db = new DBConnection(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.addBtn);
        update = findViewById(R.id.updateBtn);
        delete = findViewById(R.id.delBtn);
        table = findViewById(R.id.tableView);
        loadData();
        add.setOnClickListener(v->{
            startActivity(new Intent(this, AddStudent.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private TextView getTextView(String val){
        TextView txtView = new TextView(this);
        txtView.setText(val);
        return txtView;
    }

    private void loadData() {
        Cursor cursor = db.getAllStudents();
        table.removeViews(1, table.getChildCount()-1);
        if(cursor.getCount() == 0){
            table.addView(getTextView("No data found"));
            cursor.close();
            return;
        }
        while (cursor.moveToNext()){
            TableRow row = new TableRow(this);
            row.addView(getTextView(cursor.getString(0)));
            row.addView(getTextView(cursor.getString(1)));
            row.addView(getTextView(cursor.getString(2)));
            table.addView(row);
        }
        cursor.close();
    }
}