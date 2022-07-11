package com.example.newsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {

    private static final String DATABASE_FILE_NAME = "saved_news";

    public DataBase(Context context){super(context, DATABASE_FILE_NAME, null, 1);}

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = String.format(
                "CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                NewsModelDB.TABLE_NAME, NewsModelDB.FIELD_POST_ID, NewsModelDB.FIELD_TITLE, NewsModelDB.FIELD_AUTHOR, NewsModelDB.FIELD_PUBLISHED_AT, NewsModelDB.FIELD_IMAGE, NewsModelDB.FIELD_LINK
        );

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", NewsModelDB.TABLE_NAME));
        onCreate(db);
    }

    public void addPost (String title, String author, String published_at, String image, String link){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(NewsModelDB.FIELD_TITLE, title);
        cv.put(NewsModelDB.FIELD_AUTHOR, author);
        cv.put(NewsModelDB.FIELD_PUBLISHED_AT, published_at);
        cv.put(NewsModelDB.FIELD_IMAGE, image);
        cv.put(NewsModelDB.FIELD_LINK, link);

        db.insert(NewsModelDB.TABLE_NAME, null, cv);
    }

    public int deletePost(int postId){
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(NewsModelDB.TABLE_NAME,NewsModelDB.FIELD_POST_ID + " = ?", new String[] {String.valueOf(postId)});
    }

    public List<NewsModelDB> getAllSavedNews(){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = String.format("SELECT * FROM %s", NewsModelDB.TABLE_NAME);
        Cursor result = db.rawQuery(query, null);
        result.moveToFirst();

        List<NewsModelDB> list = new ArrayList<>(result.getCount());

        while (!result.isAfterLast()){
            int postId = result.getInt(result.getColumnIndex(NewsModelDB.FIELD_POST_ID));
            String title = result.getString(result.getColumnIndex(NewsModelDB.FIELD_TITLE));
            String author = result.getString(result.getColumnIndex(NewsModelDB.FIELD_AUTHOR));
            String published_at = result.getString(result.getColumnIndex(NewsModelDB.FIELD_PUBLISHED_AT));
            String image = result.getString(result.getColumnIndex(NewsModelDB.FIELD_IMAGE));
            String link = result.getString(result.getColumnIndex(NewsModelDB.FIELD_LINK));
            NewsModelDB post = new NewsModelDB(postId, title, author, published_at, image, link);
            list.add(post);
            result.moveToNext();
        }
        return list;

    }
}