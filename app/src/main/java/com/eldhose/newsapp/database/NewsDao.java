package com.eldhose.newsapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.eldhose.newsapp.NewsModel;

import java.util.List;

@Dao
public interface NewsDao {

        @Insert
        void insertBookMark (NewsModel newsModel);

        @Query("SELECT * FROM news_table")
        LiveData<List<NewsModel>> getBookMarks();

        @Delete
        void deleteBookMark(NewsModel newsModel);


}
