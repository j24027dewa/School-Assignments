package com.example.multifunccalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class CalcDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "calc.db";
    private static final int DB_VERSION = 1;
    public CalcDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE log (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "expression TEXT, " +
                        "result TEXT, " +
                        "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS log");
        onCreate(db);
    }

    public void addLog(String expr, String result) {
        SQLiteDatabase db = getWritableDatabase();

        // 1. 新しい履歴を追加
        ContentValues values = new ContentValues();
        values.put("expression", expr);
        values.put("result", result);
        db.insert("log", null, values);

        // 2. 最新10件に保つ（古いものを削除）
        db.execSQL(
                "DELETE FROM log WHERE id NOT IN (" +
                        "SELECT id FROM log ORDER BY timestamp DESC LIMIT 10)"
        );

        db.close();
    }

    public List<String> getLog() {
        List<String> log = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT expression, result FROM log ORDER BY timestamp DESC LIMIT 10", null);

        while (cursor.moveToNext()) {
            String expr = cursor.getString(0);
            String res = cursor.getString(1);
            log.add(expr + res);
        }

        cursor.close();
        db.close();

        return log;
    }

    public List<String> getResult() {
        List<String> log = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT expression, result FROM log ORDER BY timestamp DESC LIMIT 10", null);

        while (cursor.moveToNext()) {
            log.add(cursor.getString(1));
        }

        cursor.close();
        db.close();

        return log;
    }

}
