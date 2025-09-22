package com.example.differentlayouts;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBConnection extends SQLiteOpenHelper {
    static String name = "expenses.db";

    public DBConnection(Context context) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE expenses(id INTEGER PRIMARY KEY AUTOINCREMENT, amt INTEGER)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop = "DROP TABLE IF EXISTS expenses";
        db.execSQL(drop);
    }

    public void insert(int amt){
        SQLiteDatabase db = this.getWritableDatabase();
        String SQL  = "INSERT INTO expenses (amt) VALUES (?)";
        db.execSQL(SQL, new Object[]{amt});
    }

    public Cursor viewAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT id, amt FROM expenses";
        return db.rawQuery(sql,null);
    }

    public int viewTotal(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT sum(amt) FROM expenses";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        return  cursor.getInt(0);
    }
}
