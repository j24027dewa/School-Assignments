package com.example.multifunccalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SingleValueDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "single_value.db";
    private static final int DB_VERSION = 1;

    public SingleValueDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE value_table (id INTEGER PRIMARY KEY, value TEXT)");
        // 初期値を1件だけ挿入（id = 1）
        ContentValues initial = new ContentValues();
        initial.put("id", 1);
        initial.put("value", 0);
        db.insert("value_table", null, initial);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS value_table");
        onCreate(db);
    }

    // 値を取得
    public String getValue() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT value FROM value_table WHERE id = 1", null);
        String val = "";
        if (cursor.moveToFirst()) {
            val = cursor.getString(0) + "  ";
        }
        cursor.close();
        return val;
    }

    // 値を更新（上書き）
    public void setValue(String newValue) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("value", newValue);
        db.update("value_table", values, "id = 1", null);
    }

    // クリア
    public void clear() {
        setValue("0");
    }

    // 加算
    public void add(String delta) {
        setValue(Calculator.calculate(getValue() + "+" + delta));
    }

    // 減算
    public void sub(String delta) {
        setValue(Calculator.calculate(getValue() + "-" + delta));
    }
}

