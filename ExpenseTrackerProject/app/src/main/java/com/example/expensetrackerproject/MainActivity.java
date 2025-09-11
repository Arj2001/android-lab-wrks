package com.example.expensetrackerproject;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button addBtn;
    TextView amountView;
    TextView dateView;
    DataBaseConnection dataBaseConnection = new DataBaseConnection(this);
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    LinearLayout transactionLayout;

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
        transactionLayout = findViewById(R.id.transcationViewLayout);
        viewTransactions();
        dateView.setOnClickListener(v->{
            showDatePicker();
        });

        addBtn.setOnClickListener(v->{
            addExpense();
        });

    }

    private void viewTransactions() {
        transactionLayout.removeAllViews();
        String SQL = "SELECT * FROM expenses";
        Cursor cursor = dataBaseConnection.getReadableDatabase().rawQuery(SQL, null);
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        textView.setTextSize(18); // optional
        textView.setPadding(20, 20, 20, 20);
        if(cursor.getCount() == 0){
            textView.setText("No Transactions");
            transactionLayout.addView(textView);
            return;
        }
        while(cursor.moveToNext()){
            TextView transactionDetailsView = new TextView(this);
            transactionDetailsView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            transactionDetailsView.setTextSize(18); // optional
            transactionDetailsView.setPadding(20, 20, 20, 20);
            String val = cursor.getString(1) + "\t\t\t" + cursor.getDouble(2);
            transactionDetailsView.setText(val);
            transactionLayout.addView(transactionDetailsView);
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
        dataBaseConnection.addExpenses(date, amount);
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
