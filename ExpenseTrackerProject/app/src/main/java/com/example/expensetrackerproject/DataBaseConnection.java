package com.example.expensetrackerproject;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.database.Cursor;
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
        String CREATE_TABLE = "CREATE TABLE expenses (id Integer Primary Key Autoincrement, date Text, categ Text, amount Real)";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable = "DROP TABLE IF EXISTS expenses";
        db.execSQL(dropTable);
        onCreate(db);
    }

    public void addExpenses(String date, double amount, String categ){
        String SQL = "INSERT INTO expenses (date, amount, categ) VALUES (?, ?, ?)";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(SQL, new Object[]{date, amount, categ});
        db.close();
    }

    public Cursor getCategoryExpenses(){
        String SQL = "SELECT categ, SUM(amount) FROM expenses GROUP BY categ";
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(SQL, null);
    }
}
