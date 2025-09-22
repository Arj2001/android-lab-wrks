package com.example.studentcrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBConnection extends SQLiteOpenHelper {

    private static final String dbName = "students.db";

    public DBConnection(Context context){
        super(context, dbName, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String studentTable = "CREATE TABLE IF NOT EXISTS student(id Integer Primary key Autoincrement, name Text, class Text)";
        db.execSQL(studentTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop = "DROP TABLE IF EXISTS students";
        db.execSQL(drop);
    }

    public void insertStudent(String name, String cls){
        String insert = "INSERT INTO student(name, class) VALUES (?,?)";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(insert, new Object[]{name, cls});
    }

    public void updateStudent(int id, String name, String cls){
        SQLiteDatabase db = this.getWritableDatabase();
        String update = "UPDATE student SET name = ?,cls = ? WHERE id = ?";
        db.execSQL(update, new Object[]{name, cls, id});
    }

    public Cursor getStudent(int id){
        SQLiteDatabase db =this.getReadableDatabase();
        String sql = "SELECT * FROM student WHERE id = ?";
        return  db.rawQuery(sql, new String[]{String.valueOf(id)});
    }

    public Cursor getAllStudents(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM student";
        return db.rawQuery(sql, null);

    }
}