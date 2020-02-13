/*
package com.farzain.watchmovie.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.farzain.watchmovie.Movie;

import java.util.ArrayList;

import static com.farzain.watchmovie.db.DatabaseContract.TABLE_MOVIE;
import static com.farzain.watchmovie.db.DatabaseContract.MovieColumns.TITLE;
import static com.farzain.watchmovie.db.DatabaseContract.MovieColumns.OVERVIEW;
import static com.farzain.watchmovie.db.DatabaseContract.MovieColumns.RELESE_DATE;
import static com.farzain.watchmovie.db.DatabaseContract.MovieColumns.POSTER_PATH;

public class MovieHelper {
    private static final String DATABASE_TABLE = TABLE_MOVIE;
    private static DatabaseHelper dataBaseHelper;
    private static MovieHelper INSTANCE;
    private static SQLiteDatabase database;

    private MovieHelper(Context context) {
        dataBaseHelper = new DatabaseHelper(context);
    }

    public static MovieHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MovieHelper(context);
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

    public ArrayList<Movie> getAllMovies() {
        ArrayList<Movie> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                BaseColumns._ID + " ASC",
                null);
        cursor.moveToFirst();
        Movie movie;
        if (cursor.getCount() > 0) {
            do {
                movie = new Movie();
                movie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID)));
                movie.setName(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                movie.setRelease(cursor.getString(cursor.getColumnIndexOrThrow(RELESE_DATE)));
                movie.setPhoto(cursor.getString(cursor.getColumnIndexOrThrow(POSTER_PATH)));
                movie.setSynopsis(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));
                arrayList.add(movie);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(Movie movie) {
        ContentValues args = new ContentValues();
        args.put(BaseColumns._ID, movie.getId());
        args.put(TITLE, movie.getName());
        args.put(OVERVIEW, movie.getSynopsis());
        args.put(RELESE_DATE, movie.getRelease());
        args.put(POSTER_PATH, movie.getPhoto());
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
