package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

 public    static  final String DATABASE_NAME="UserDatabse.db";
 public    static  final String TABLE_NAME="USERS";
 public    static  final  int DATABASE_VERSION=1;
 public  static final  String Txt_Id="ID";
 public static  final String Txt_Username="USERNAME";
 public static final String TXt_Name="NAME";

    public DatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,1);
    }

   @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
      sqLiteDatabase.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT,NAME TEXT)");
    }
    public boolean insertData(String Username,String Name)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Txt_Username,Username);
        contentValues.put(TXt_Name,Name);
        long result=sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        if (result ==-1)
        {
           return false;
        }
        else
        {
          return true;
        }
    }
    public Cursor getViewData()
    {
        SQLiteDatabase database=this.getWritableDatabase();
        Cursor cursor=database.rawQuery("select * from "+TABLE_NAME,null);
        return cursor;
    }
    public Integer dataDelete(String id)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME,"ID=?",new String[]{id});
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
