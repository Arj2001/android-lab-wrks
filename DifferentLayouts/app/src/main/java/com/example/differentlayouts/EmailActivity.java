package com.example.differentlayouts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EmailActivity extends AppCompatActivity {

    TextView toAddress, subject, bodyText;
    Button mailBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_email);

        toAddress = findViewById(R.id.toAddress);
        subject = findViewById(R.id.subject);
        bodyText = findViewById(R.id.bodyText);
        mailBtn = findViewById(R.id.mailBtn);

        mailBtn.setOnClickListener(v->{
            String to = toAddress.getText().toString();
            String sub = subject.getText().toString();
            String body = bodyText.getText().toString();

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
            intent.putExtra(Intent.EXTRA_SUBJECT, sub);
            intent.putExtra(Intent.EXTRA_TEXT, body);
            startActivity(Intent.createChooser(intent, "Choose an email client"));
        });
    }
}