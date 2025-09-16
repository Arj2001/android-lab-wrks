package com.example.expensetrackerproject;

import android.database.Cursor;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ReportsPage extends AppCompatActivity {

    PieChart pieChart;
    DataBaseConnection db= new DataBaseConnection(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reports_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        pieChart  = findViewById(R.id.pieChart);
        displayPieChart();

    }

    private void displayPieChart() {
        ArrayList<PieEntry> entries= new ArrayList<>();
        Cursor cursor = db.getCategoryExpenses();
        if(cursor.getCount() == 0 ) {
            return;
        }
        while(cursor.moveToNext()){
            entries.add(new PieEntry(cursor.getFloat(1), cursor.getString(0)));
        }
        cursor.close();

        PieDataSet pieDataSet = new PieDataSet(entries, "Expense Category");
        pieDataSet.setValueTextSize(16f);
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        pieChart.setData(new PieData(pieDataSet));
        pieChart.getDescription().setEnabled(false);
        pieChart.invalidate();
    }
}