package com.example.differentlayouts;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivityCustomGrid extends AppCompatActivity {


    GridView gridView;
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
    String[] numbers ={
            "1234567890",
            "1234567890",
            "1234567890",
            "1234567890",
            "1234567890",
            "1234567890",
            "1234567890",
            "1234567890",
            "1234567890",
            "1234567890"
    };

    class CustomAdapter extends BaseAdapter{

        private String[] names;
        private String[] numbers;
        private Context context;
        public CustomAdapter(Context context, String[] names, String[] numbers){
                this.context = context;
                this.names = names;
                this.numbers = numbers;
        }
        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public Object getItem(int position) {
            return names[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout linearLayout;
            if (convertView == null) {
                linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                TextView textView = new TextView(context);
                textView.setTextSize(20);
                TextView textView1 = new TextView(context);
                textView1.setTextSize(16);
                linearLayout.addView(textView);
                linearLayout.addView(textView1);
                convertView = linearLayout;
            } else {
                linearLayout = (LinearLayout) convertView;
            }
            TextView textView = (TextView) linearLayout.getChildAt(0);
            TextView textView1 = (TextView) linearLayout.getChildAt(1);
            textView.setText(names[position]);
            textView1.setText(numbers[position]);

            return convertView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_gridview);

        gridView = findViewById(R.id.gridView);
        CustomAdapter customAdapter = new CustomAdapter(this, contacts, numbers);
        gridView.setAdapter(customAdapter);

    }


}