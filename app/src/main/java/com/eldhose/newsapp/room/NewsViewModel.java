package com.eldhose.newsapp.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.eldhose.newsapp.NewsModel;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {

    private NewsRepository newsRepository;

    LiveData<List<NewsModel>> mBookMarks;

    public NewsViewModel(@NonNull Application application) {
        super(application);
        newsRepository = new NewsRepository(application);
        mBookMarks=newsRepository.getmBookMArks();

    }

    public LiveData<List<NewsModel>> getBookMarks()
    {return mBookMarks;}

    public void deleteBookMark(NewsModel newsModel)
    { newsRepository.insertmBookMarks(newsModel);}

    public void insertBookMark(NewsModel newsModel)
    { newsRepository.insertmBookMarks(newsModel);}

    public MutableLiveData<List<NewsModel>> getAllNews (String s)
    { return newsRepository.getmAllNews(s); }

    public MutableLiveData<List<NewsModel>> getTopHeadlines ()
    { return newsRepository.getmTopHeadline(); }

    public MutableLiveData<List<NewsModel>> getNewsByCategory (String c)
    { return newsRepository.getmNewsByCategory(c); }


}
