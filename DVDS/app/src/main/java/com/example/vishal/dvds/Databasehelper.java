package com.example.vishal.dvds;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Databasehelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="vision1.db";
    public static final String TABLE_NAME="vision1";
    public static final String Col_1="EMAIL";
    public static final String Col_2="PASSWORD";
    public static final String Col_3="NAME";
    public static final String Col_4="AGE";
    public static final String Col_5="ADDRESS";
    public static final String Col_6="CONTACT";
    public static final String Col_7="WORK";


    SQLiteDatabase db;

    public Databasehelper(Context context) {
        super(context,DATABASE_NAME, null,1);
        SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table vision1(EMAIL TEXT PRIMARY KEY,PASSWORD TEXT NOT NULL," +
                "NAME TEXT NOT NULL,AGE TEXT NOT NULL,ADDRESS TEXT NOT NULL," +
                "CONTACT TEXT NOT NULL,WORK TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean inserdata(String email,String password,
                             String name,String age,String address,String contact,String work)
    {
        db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Col_1,email);
        contentValues.put(Col_2,password);
        contentValues.put(Col_3,name);
        contentValues.put(Col_4,age);
        contentValues.put(Col_5,address);
        contentValues.put(Col_6,contact);
        contentValues.put(Col_7,work);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
        {return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor retriveemployee(String email)
    { db=this.getReadableDatabase();
        String a=email;
        Cursor c=db.query(false,TABLE_NAME,new String[] {Col_1,Col_2},
                Col_1+ "=" + "'"+a+"'",null,null,null,null,null);
        if(c!=null){c.moveToFirst();} return c;
    }
    public Cursor retriveemployee2(String iid)
    { db=this.getReadableDatabase();
        String a=iid;
        Cursor c=db.query(false,TABLE_NAME,new String[] {Col_1,Col_2,Col_3,Col_4,Col_5,Col_6,Col_7},
                Col_1+ "=" + "'"+a+"'",null,null,null,null,null);
        if(c!=null){c.moveToFirst();} return c;

    }

}

