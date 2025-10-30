package com.example.differentlayouts;

import static android.widget.Toast.LENGTH_SHORT;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SMSActivity extends AppCompatActivity {

    EditText smsBox;
    Button sendBtn;
    EditText toNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_smsactivity);

        smsBox = findViewById(R.id.smsBox);
        toNum = findViewById(R.id.toNum);
        sendBtn = findViewById(R.id.sendBtn);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);

        }
        sendBtn.setOnClickListener(v);

    }

    View.OnClickListener v = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(toNum.getText().toString(),null, smsBox.getText().toString(),null, null);
            Toast.makeText(SMSActivity.this, "SMS sent",  LENGTH_SHORT).show();
        }
    };
}