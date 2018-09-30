package com.example.felix_its.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.os.Build.ID;

public class DBHelper extends SQLiteOpenHelper {
    //Declare Variable
    public static final String DATABASE_NAME="Student.db";
    public static final String TABLE_NAME="student_table";
    public static final String COL1="ID";
    public static final String COL2="NAME";
    public static final String COL3="SURNAME";
    public static final String COL4="MARKS";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table " + TABLE_NAME +" (ID integer primary key autoincrement,NAME TEXT,SURNAME TEXT,MARKS INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name,String surname,String marks){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL2,name);
        contentValues.put(COL3,surname);
        contentValues.put(COL4,marks);
        long result =db.insert(DATABASE_NAME,null,contentValues);
        if (result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }


    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
}

