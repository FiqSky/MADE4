/*
package com.farzain.watchmovie.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.farzain.watchmovie.Series;

import org.json.JSONObject;

import java.util.ArrayList;

import static com.farzain.watchmovie.db.DatabaseContract.SeriesColumns.TABLE_SERIES;
import static com.farzain.watchmovie.db.DatabaseContract.SeriesColumns.TITLE;
import static com.farzain.watchmovie.db.DatabaseContract.SeriesColumns.OVERVIEW;
import static com.farzain.watchmovie.db.DatabaseContract.SeriesColumns.RELESE_DATE;
import static com.farzain.watchmovie.db.DatabaseContract.SeriesColumns.POSTER_PATH;

public class SeriesHelper {
    private static final String DATABASE_TABLE = TABLE_SERIES ;
    private static DatabaseHelper dataBaseHelper;
    private static SeriesHelper INSTANCE;
    private static SQLiteDatabase database;

    private SeriesHelper(Context context) {
        dataBaseHelper = new DatabaseHelper(context);
    }

    public static SeriesHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SeriesHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = dataBaseHelper.getWritableDatabase();
    }

    public void close() {
        dataBaseHelper.close();
        if (database.isOpen())
            database.close();
    }

    public ArrayList<Series> getAllTv() {
        ArrayList<Series> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                BaseColumns._ID + " ASC",
                null);
        cursor.moveToFirst();
        Series series;
        if (cursor.getCount() > 0) {
            do {
                series = new Series();
                series.setId(cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID)));
                series.setName(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                series.setSynopsis(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));
                series.setPhoto(cursor.getString(cursor.getColumnIndexOrThrow(POSTER_PATH)));
                arrayList.add(series);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(Series series) {
        ContentValues args = new ContentValues();
        args.put(BaseColumns._ID, series.getId());
        args.put(TITLE, series.getName());
        args.put(OVERVIEW, series.getSynopsis());
        args.put(POSTER_PATH, series.getPhoto());
        return database.insert(DATABASE_TABLE, null, args);
    }


    public int delete(int id) {
        return database.delete(DATABASE_TABLE, BaseColumns._ID + " = '" + id + "'", null);
    }

    public boolean isExist(int id) {
        String query = "SELECT * FROM " + DATABASE_TABLE + " WHERE " + BaseColumns._ID + " =?";
        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(id)});
        boolean exist = false;
        if (cursor.moveToFirst()) {
            exist = true;
        }
        cursor.close();
        return exist;
    }
}
*/
