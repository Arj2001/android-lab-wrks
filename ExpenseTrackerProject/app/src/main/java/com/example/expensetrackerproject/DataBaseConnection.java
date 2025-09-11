package com.example.expensetrackerproject;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseConnection extends SQLiteOpenHelper {

    private static final String name = "ExpenseTracker";
    private static final SQLiteDatabase.CursorFactory factory = null;
    private static final int version = 1;

    public DataBaseConnection(Context context) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE expenses (id Integer Primary Key Autoincrement, date Text, amount Real)";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable = "DROP TABLE IF EXISTS expenses";
        db.execSQL(dropTable);
        onCreate(db);
    }

    public void addExpenses(String date, double amount){
        String SQL = "INSERT INTO expenses (date, amount) VALUES (?, ?)";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(SQL, new Object[]{date, amount});
        db.close();
    }
}
