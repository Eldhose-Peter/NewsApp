package com.eldhose.newsapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.eldhose.newsapp.NewsModel;

@Database(entities = {NewsModel.class},version = 1,exportSchema = false)
public abstract class NewsDB extends RoomDatabase {

    public abstract NewsDao newsDao();

    public static NewsDB INSTANCE;

    public static NewsDB getDatabase(final Context context)
    {
        if (INSTANCE == null) {
            synchronized (NewsDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NewsDB.class, "news_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
