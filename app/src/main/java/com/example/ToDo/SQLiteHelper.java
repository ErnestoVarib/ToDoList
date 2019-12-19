package com.example.ToDo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String CREAR_TABLA_TODO = "CREATE TABLE todo(_id integer primary key autoincrement, texto TEXT, completado TEXT)";
    private static final String DB_NAME = "todo.db";
    private static final int DB_VERSION = 1;
    public SQLiteHelper(Context context ){
        super(context, DB_NAME, null, DB_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREAR_TABLA_TODO);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
        onCreate(db);
    }
}
