package com.example.differentlayouts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button getDetailsBtn;
    TextView dataDisp;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_async);

        getDetailsBtn = findViewById(R.id.getDetailsBtn);
        dataDisp = findViewById(R.id.dataDisp);
        progressBar = findViewById(R.id.progressBar);

        getDetailsBtn.setOnClickListener(v -> {
            new GetData().execute();
        });

    }

    private class GetData extends AsyncTask<Void, Integer, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(TextView.VISIBLE);
            progressBar.setProgress(0);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
            dataDisp.setText("Progress: " + values[0] + "%");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(TextView.GONE);
            dataDisp.setText(s);
        }

        @Override
        protected String doInBackground(Void... voids) {
            for (int i = 1; i <= 10; i++) {
                try {
                    Thread.sleep(500);  // simulate work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i * 10);  // update progress
            }
            return "New Data is this:\nLorem ipsum dolor sit amet";
        }
    }
}
