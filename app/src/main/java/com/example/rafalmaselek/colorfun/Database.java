package com.example.rafalmaselek.colorfun;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Database extends SQLiteOpenHelper {
    private static String db_name = "Normal Database";
    private static int db_ver = 1;

    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, db_name, null, db_ver);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table IF NOT EXISTS Easy_results("
                + "nr integer primary key autoincrement," + "username text,"
                + "result integer," + "max_possible_result integer);" + "");
        db.execSQL("create table IF NOT EXISTS Normal_results("
                + "nr integer primary key autoincrement," + "username text,"
                + "result integer," + "max_possible_result integer);" + "");
        db.execSQL("create table IF NOT EXISTS Hard_results("
                + "nr integer primary key autoincrement," + "username text,"
                + "result integer," + "max_possible_result integer);" + "");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void addResult(String username, int result, int mpresult, String level) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username", username);
        values.put("result", result);
        values.put("max_possible_result", mpresult);
        Log.i("LEVEL:  ", level);
        if(level.contains("NORMAL"))
            db.insert("Normal_results", null, values);
        else if(level.contains("EASY"))
            db.insert("Easy_results", null, values);
        else if(level.contains("HARD"))
            db.insert("Hard_results", null, values);


    }
    public void actualizeResult(String name, int newResult, int newMPResult) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        Result r = getResultByName(name);
        if(r.getResult() < newResult)
            values.put("username", name);
            values.put("result", newResult);
            values.put("max_possible_result", newMPResult);

            String[] argumenty = { "" + name };
            db.update("Normal_results", values, "username=?", argumenty);
    }
    public Cursor getAllResults(String level) {
        SQLiteDatabase db = getReadableDatabase();
        String[] kolumny = { "nr", "username", "result", "max_possible_result"};
        Cursor kursor;
        if(level.contains("NORMAL"))
            kursor = db.query("Normal_results", kolumny, null, null, null, null, "result"+" DESC");
        else if(level.contains("EASY"))
            kursor = db.query("Easy_results", kolumny, null, null, null, null, "result"+" DESC");
        else
            kursor = db.query("Hard_results", kolumny, null, null, null, null, "result" + " DESC");


        return kursor;
    }

    public Result getResultByName(String name){
        Result r;
        SQLiteDatabase db = getReadableDatabase();
        String[] kolumny = { "nr", "username", "result", "max_possible_result"};
        String args[] = {""+name};
        Cursor kursor = db.query("Normal_results", kolumny, " username=?", args, null, null, null);
        if(kursor != null) {
            kursor.moveToNext();
            r = new Result(kursor.getInt(0), kursor.getString(1), kursor.getInt(2), kursor.getInt(3));
            return r;
        }
        return null;

    }

    public Result getResultByID(int id, String level){
        Result r;
        SQLiteDatabase db = getReadableDatabase();
        String[] kolumny = { "nr", "username", "result", "max_possible_result"};
        String args[] = {""+id};
        Cursor kursor;
        if(level.contains("NORMAL"))
            kursor = db.query("Normal_results", kolumny, " nr=?", args, null, null, null);
        else if(level.contains("EASY"))
            kursor = db.query("Easy_results", kolumny, " nr=?", args, null, null, null);
        else
            kursor = db.query("Hard_results", kolumny, " nr=?", args, null, null, null);
        if(kursor != null) {
            kursor.moveToNext();
            r = new Result(kursor.getInt(0), kursor.getString(1), kursor.getInt(2), kursor.getInt(3));
            return r;
        }
        return null;

    }
}