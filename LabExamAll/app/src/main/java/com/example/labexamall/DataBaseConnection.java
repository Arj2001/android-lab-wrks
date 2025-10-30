package com.example.labexamall;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseConnection extends SQLiteOpenHelper {


    public DataBaseConnection(Context context){
        super(context, "employee", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS EMPLOYEE(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT, SALARY INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String name, String salary){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("insert into employee(name, salary) values(?,?)", new Object[]{name, salary});
    }

    public String display(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from employee", null);
        String val = "";
        while(c.moveToNext()){
            val+=c.getString(0)+" "+c.getString(1)+" "+c.getString(2)+" \n";
        }
        return  val;
    }
}
