package com.example.expensetrackerproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button addBtn;
    TextView amountView;
    TextView dateView;
    Spinner categView;
    String categorySelected;
    DataBaseConnection dataBaseConnection = new DataBaseConnection(this);
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addBtn = findViewById(R.id.addBtn);
        amountView = findViewById(R.id.editAmount);
        dateView = findViewById(R.id.editDate);
        tableLayout = findViewById(R.id.tableViewLayout);
        categView = findViewById(R.id.selectCateg);
        viewTransactions();
        dateView.setOnClickListener(v->{
            showDatePicker();
        });

        categView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categorySelected = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addBtn.setOnClickListener(v->{
            addExpense();
        });

        findViewById(R.id.reportBtn).setOnClickListener(v->{
            Intent intent = new Intent(this, ReportsPage.class);
            startActivity(intent);
        });

    }

    private TextView createTableTextView(String text){
        TextView textView = new TextView(this);
        textView.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
        ));
//        textView.setTextSize(18); // optional
//        textView.setPadding(20, 20, 20, 20);
        textView.setText(text);
        return textView;
    }
    private void viewTransactions() {
        tableLayout.removeViews(1, tableLayout.getChildCount() - 1);
        String SQL = "SELECT * FROM expenses";
        Cursor cursor = dataBaseConnection.getReadableDatabase().rawQuery(SQL, null);

        if(cursor.getCount() == 0){
            tableLayout.addView(createTableTextView("No Transactions"));
            return;
        }
        while(cursor.moveToNext()){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT
            ));
            tableRow.addView(createTableTextView(cursor.getString(1)));
            tableRow.addView(createTableTextView(cursor.getString(2)));
            tableRow.addView(createTableTextView(cursor.getString(3)));
            tableLayout.addView(tableRow);
        }
        cursor.close();
    }

    private void addExpense() {
        String date = dateView.getText().toString();
        Double amount = Double.parseDouble(amountView.getText().toString());
        if(date.isEmpty() || amount == 0){
            Toast.makeText(this, "Please enter all field", Toast.LENGTH_SHORT).show();
            return;
        }
        dataBaseConnection.addExpenses(date, amount, categorySelected);
        viewTransactions();
        Toast.makeText(this, "Expense added successfully", Toast.LENGTH_SHORT).show();

    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        dateView.setText(dateFormat.format(calendar.getTime()));
                    }
                },year, month, day
        );
        datePickerDialog.show();
    }
}
