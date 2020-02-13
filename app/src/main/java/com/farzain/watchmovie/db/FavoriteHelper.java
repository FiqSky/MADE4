package com.farzain.watchmovie.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import com.farzain.watchmovie.Movie;
import com.farzain.watchmovie.Series;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.farzain.watchmovie.db.DatabaseContract.TABLE_MOVIE;
import static com.farzain.watchmovie.db.DatabaseContract.MovieColumns.ID;
import static com.farzain.watchmovie.db.DatabaseContract.MovieColumns.TITLE;
import static com.farzain.watchmovie.db.DatabaseContract.MovieColumns.OVERVIEW;
import static com.farzain.watchmovie.db.DatabaseContract.MovieColumns.RELESE_DATE;
import static com.farzain.watchmovie.db.DatabaseContract.MovieColumns.POSTER_PATH;
import static com.farzain.watchmovie.db.DatabaseContract.TABLE_SERIES;
import static com.farzain.watchmovie.db.DatabaseContract.SeriesColumns.ID_SERIES;
import static com.farzain.watchmovie.db.DatabaseContract.SeriesColumns.TITLE_SERIES;
import static com.farzain.watchmovie.db.DatabaseContract.SeriesColumns.OVERVIEW_SERIES;
import static com.farzain.watchmovie.db.DatabaseContract.SeriesColumns.RELESE_DATE_SERIES;
import static com.farzain.watchmovie.db.DatabaseContract.SeriesColumns.POSTER_PATH_SERIES;

public class FavoriteHelper {
    private static final String DATABASE_MOVIE = TABLE_MOVIE;
    private static final String DATABASE_SERIES = TABLE_SERIES;
    private static DatabaseHelper databaseHelper;
    private static FavoriteHelper INSTANCE;
    private static SQLiteDatabase database;

    // Constructor untuk FavoriteItemsHelper
    public FavoriteHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    // Method tsb berguna untuk menginisiasi database
    public static FavoriteHelper getInstance(Context context) {
        if(INSTANCE == null) {
            synchronized(SQLiteOpenHelper.class) {
                if(INSTANCE == null) {
                    INSTANCE = new FavoriteHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    // Open connection to database
    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
        Log.d(TAG, "open: " + database.isOpen());
    }

    // Close connection from database
    public void close() {
        databaseHelper.close();
        // Cek jika database sedang connected, jika iya maka disconnect
        if(database.isOpen())
            database.close();
        Log.d(TAG, "close: "+database.isOpen());
    }

    // Method untuk read data dari DB dengan menggunakan SQLiteDatabase query method (table movie item)
    public ArrayList<Movie> getAllFavoriteMovie() {
        ArrayList<Movie> favoriteMovieArrayList = new ArrayList<>();

        // Call SQLiteDatabase query method dgn sort Date Added Column in descending order (most recent to least recent)
        Cursor cursor = database.query(DATABASE_MOVIE,
                null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        // Memindahkan Cursor ke baris pertama
        cursor.moveToFirst();
        // Initialize variable yg return MovieItem
        Movie movie;
        if(cursor.getCount() > 0) {
            do {
                movie = new Movie();
                movie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID)));
                movie.setName(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                movie.setSynopsis(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));
                Log.d(TAG, "getAllFavoriteMovie: Data getSynopsis"+ movie.getSynopsis());
                movie.setRelease(cursor.getString(cursor.getColumnIndexOrThrow(RELESE_DATE)));
                movie.setPhoto(cursor.getString(cursor.getColumnIndexOrThrow(POSTER_PATH)));
//                 Add movie item data ke ArrayList
                favoriteMovieArrayList.add(movie);
//                 Memindahkan Cursor ke baris selanjutnya
                cursor.moveToNext();
            }
            while(!cursor.isAfterLast()); // Loop kondisi ini adalah ketika cursornya itu belum berada di baris terakhir
        }
        // Close the Cursor
        cursor.close();
        return favoriteMovieArrayList;
    }

    public long insertMovie(Movie movie) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,movie.getId());
        contentValues.put(TITLE, movie.getName());
        Log.d(TAG, "insertMovie: " + movie.getName());
        contentValues.put(OVERVIEW, movie.getSynopsis());
        contentValues.put(RELESE_DATE, movie.getRelease());
        contentValues.put(POSTER_PATH, movie.getPhoto());
        Log.d(TAG, "insertMovie: " + contentValues);
        return database.insert(DATABASE_MOVIE,null,contentValues);
    }

    // Method untuk Update data ke DB dengan menggunakan SQLiteDatabase insert method (table movie item)
    public int updateMovie(Movie movie) {
        // Create ContentValues object
        ContentValues contentValues = new ContentValues();
        // Insert value ke ContentValues object
        contentValues.put(ID,movie.getId());
        contentValues.put(TITLE, movie.getName());
        contentValues.put(OVERVIEW, movie.getSynopsis());
        contentValues.put(RELESE_DATE, movie.getRelease());
        contentValues.put(POSTER_PATH, movie.getPhoto());
        // Execute SQLiteDatabase insert method
        return database.update(DATABASE_MOVIE, contentValues, _ID + "= '" + movie.getName() + "'", null);
    }

    public int deleteMovie(int inamed) {
        return database.delete(DATABASE_MOVIE, _ID + " = '" + inamed + "'", null);
    }

    /*public boolean checkMovie(int name) {
        String query = "SELECT * FROM " + DATABASE_MOVIE + " WHERE " + _ID + " =?";
        Log.d(TAG, "checkMovie: "+ query);
        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(name)});
        Log.d(TAG, "checkMovie: " + cursor);
        boolean exist = false;
        if (cursor.moveToFirst()) {
            exist = true;
        }
        cursor.close();
        return exist;
    }*/
    public boolean checkMovie(String name){
        Cursor cursor = null;
        String sql = "SELECT * FROM "+DATABASE_MOVIE+" WHERE "+_ID +"="+name;
        cursor = database.rawQuery(sql, null);
        System.out.println("cek sini"+cursor.getCount());

        if (cursor.getCount()>0){
            return true;
        }else {
        }
        cursor.close();
        return false;
    }

    public ArrayList<Series> getAllFavoriteSeries() {
        ArrayList<Series> favoriteSeriesArrayList = new ArrayList<>();

        // Call SQLiteDatabase query method dgn sort Date Added Column in descending order (most recent to least recent)
        Cursor cursor = database.query(DATABASE_SERIES,
                null,
                null,
                null,
                null,
                null,
                _ID + " DESC", // Data yg paling recent menjadi yang pertama
                null);
        // Memindahkan Cursor ke baris pertama
        cursor.moveToFirst();
        // Initialize variable yg return MovieItem
        Series series;
        if(cursor.getCount() > 0) {
            do {
                series = new Series();
//                 Insert value ke MovieItem bedasarkan table yg ada di FavoriteMovieItemColumns
                series.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                series.setName(cursor.getString(cursor.getColumnIndexOrThrow(TITLE_SERIES)));
                series.setRelease(cursor.getString(cursor.getColumnIndexOrThrow(RELESE_DATE_SERIES)));
                series.setPhoto(cursor.getString(cursor.getColumnIndexOrThrow(POSTER_PATH_SERIES)));
                series.setSynopsis(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW_SERIES)));
//                 Add movie item data ke ArrayList
                favoriteSeriesArrayList.add(series);
//                 Memindahkan Cursor ke baris selanjutnya
                cursor.moveToNext();

            }
            while(! cursor.isAfterLast()); // Loop kondisi ini adalah ketika cursornya itu belum berada di baris terakhir
        }

        // Close the Cursor
        cursor.close();
        return favoriteSeriesArrayList;
    }

    // Method untuk insert data ke DB dengan menggunakan SQLiteDatabase insert method (table movie item)
    public long insertSeries(Series series) {
        // Create ContentValues object
        ContentValues contentValues = new ContentValues();
        // Insert value ke ContentValues object
        contentValues.put(TITLE_SERIES, series.getName());
        contentValues.put(OVERVIEW_SERIES, series.getSynopsis());
        contentValues.put(RELESE_DATE_SERIES, series.getRelease());
        contentValues.put(POSTER_PATH_SERIES, series.getPhoto());
        // Execute SQLiteDatabase insert method
        return database.insert(DATABASE_SERIES, null, contentValues);
    }

    // Method untuk Update data ke DB dengan menggunakan SQLiteDatabase insert method (table movie item)
    public int updateSeries(Series series) {
        // Create ContentValues object
        ContentValues contentValues = new ContentValues();
        // Insert value ke ContentValues object
        contentValues.put(TITLE_SERIES, series.getName());
        contentValues.put(OVERVIEW_SERIES, series.getSynopsis());
        contentValues.put(RELESE_DATE_SERIES, series.getRelease());
        contentValues.put(POSTER_PATH_SERIES, series.getPhoto());
        // Execute SQLiteDatabase insert method
        return database.update(DATABASE_SERIES, contentValues, TITLE_SERIES + "= '" + series.getName() + "'", null);
    }

    public int deleteSeries(int id) {
        return database.delete(DATABASE_SERIES, TITLE_SERIES + " = '" + id + "'", null);
    }
/*public boolean checkMovie(String name) {
//        database = DatabaseHelper.getWritableDatabase();
        String selectString = "SELECT * FROM " + TABLE_MOVIE + " WHERE " + TITLE + " =?";
        Cursor cursor = database.rawQuery(selectString, new String[]{name});
        boolean checkMovie = false;
        if (cursor.moveToFirst()) {
            checkMovie = true;
            int count = 0;
            while (cursor.moveToNext()) {
                count++;
            }
            Log.d(TAG, String.format("%d records found", count));
        }
        cursor.close();
        return checkMovie;
    }*/
}
