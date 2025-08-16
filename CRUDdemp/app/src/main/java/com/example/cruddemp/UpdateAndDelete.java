package com.example.cruddemp;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UpdateAndDelete extends AppCompatActivity {

    private SQLiteDatabase db;
    private Button upBtn;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = openOrCreateDatabase("student_db",MODE_PRIVATE,null);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_and_delete);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        upBtn = findViewById(R.id.updateBtn);
        TextView tabIdView = findViewById(R.id.tabId);
        textView = findViewById(R.id.newName);

        upBtn.setOnClickListener(v->{
            String tabId = tabIdView.getText().toString();
            Toast.makeText(this,"table id: "+tabId, LENGTH_SHORT).show();
            ContentValues values = new ContentValues();
            values.put("username", textView.getText().toString()); // Assuming "phone_number" is your column name
            int val = db.update("STUDENT",values, "id = ?", new String[]{tabId});
            if(val!=0){
                Toast.makeText(this,"Success", LENGTH_LONG).show();
            }else{
                Toast.makeText(this,"Failed", LENGTH_LONG).show();
            }
        });

    }
}