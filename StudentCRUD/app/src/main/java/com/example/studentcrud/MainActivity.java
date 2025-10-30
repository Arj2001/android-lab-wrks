package com.example.studentcrud;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

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

    private Button getUpdateBtn(String id){
        Button update = new Button(this);
        update.setText("edit");
        TableRow.LayoutParams params = new TableRow.LayoutParams(
                50,
                TableRow.LayoutParams.WRAP_CONTENT
        );
        update.setLayoutParams(params);
        update.setBackgroundColor(Color.GREEN);
        update.setPadding(0,0,0,0);
        update.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, AddStudent.class);
            intent.putExtra("ID",id);
            intent.putExtra("EDIT",true);
            startActivity(intent);

        });
        return update;
    }

    private LinearLayout getActionButtons(int id) {

        LinearLayout buttonLayout = new LinearLayout(this);
        buttonLayout.setOrientation(LinearLayout.HORIZONTAL);

        Button update = new Button(this);
        update.setText("edit");

        LinearLayout.LayoutParams updateParams = new LinearLayout.LayoutParams(
                120,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        update.setLayoutParams(updateParams);
        update.setBackgroundColor(Color.GREEN);
        update.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddStudent.class);
            intent.putExtra("ID", id);
            startActivity(intent);
        });


        Button delete = new Button(this);
        delete.setText("delete");
        LinearLayout.LayoutParams deleteParams = new LinearLayout.LayoutParams(
                150,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        deleteParams.setMarginStart(8);
        delete.setLayoutParams(deleteParams);
        delete.setBackgroundColor(Color.RED);
        delete.setOnClickListener(v -> {
            db.deleteStudent(id);
            Toast.makeText(this,"Deletion success",LENGTH_SHORT).show();
            loadData();
        });

        buttonLayout.addView(update);
        buttonLayout.addView(delete);

        return buttonLayout;
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
            row.addView(getActionButtons(cursor.getInt(0)));
            table.addView(row);

        }
        cursor.close();
    }
}