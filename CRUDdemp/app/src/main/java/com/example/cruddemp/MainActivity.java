package com.example.cruddemp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    private Button viewBtn;
    private Button regBtn;
    private Button delBtn;
    private Button changViewBtn;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = openOrCreateDatabase("student_db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE if not exists "+
                "STUDENT ( ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "USERNAME TEXT, PASSWORD TEXT)");
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        changViewBtn = findViewById(R.id.chngView);
        changViewBtn.setOnClickListener(v->{

            Intent intent = new Intent(getApplicationContext(), UpdateAndDelete.class);
            startActivity(intent);
        });

        viewData();
        viewBtn = findViewById(R.id.button);
        viewBtn.setOnClickListener(v->viewData());

        regBtn = findViewById(R.id.regBtn);
        regBtn.setOnClickListener(v->insertData());

        delBtn = findViewById(R.id.deleteBtn);
        delBtn.setOnClickListener(v->deleteData());

    }

    private void deleteData() {
        TextView delId = findViewById(R.id.deleteId);
        int id = Integer.parseInt(delId.getText().toString());
        db.execSQL("DELETE FROM STUDENT WHERE ID = '"+id+"'");
        delId.setText(null);
    }

    private void viewData(){
        StringBuilder result = new StringBuilder();
        textView = findViewById(R.id.textView);
        Cursor cursor = db.rawQuery("SELECT * FROM STUDENT", null);
        if(cursor.getCount()==0){
            textView.setText("no data found");
            return;
        }
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String username = cursor.getString(1);
            String password = cursor.getString(2);
            result.append("ID: "+ id+"\tUser: "+username+"\tPassword: "+password+"\n");
        }
        cursor.close();
        textView.setText(result.toString());
    }

    private void insertData(){
        TextView username = findViewById(R.id.username);
        TextView password = findViewById(R.id.password);
        String query = "INSERT INTO STUDENT(USERNAME, PASSWORD) VALUES('"+username.getText().toString()+"','"+password.getText().toString()+"')";
        db.execSQL(query);
    }
}