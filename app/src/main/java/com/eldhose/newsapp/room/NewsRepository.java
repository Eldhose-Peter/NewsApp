package com.eldhose.newsapp.room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.eldhose.newsapp.api.ApiService;
import com.eldhose.newsapp.NewsModel;
import com.eldhose.newsapp.database.NewsDB;
import com.eldhose.newsapp.database.NewsDao;

import java.util.List;

public class NewsRepository {

    private ApiService apiService;
    private NewsDao mDao;
    private LiveData<List<NewsModel>> mBookMarks;


    public NewsRepository(Application application) {

        apiService = new ApiService(application);

        NewsDB db = NewsDB.getDatabase(application);
        mDao=db.newsDao();
        mBookMarks=mDao.getBookMarks();
    }

    LiveData<List<NewsModel>> getmBookMArks(){
        return mBookMarks;
    }

    void insertmBookMarks(NewsModel newsModel) {
        new insertAsyncTask(mDao).execute(newsModel);
    }

    private static class insertAsyncTask extends AsyncTask<NewsModel,Void,Void> {

        private NewsDao mAsyncTask;

        public insertAsyncTask(NewsDao mDao) {
            mAsyncTask =mDao;
        }

        @Override
        protected Void doInBackground(NewsModel... params) {
            mAsyncTask.insertBookMark(params[0]);
            return null;
        }
    }

    void deletemBookMark(NewsModel newsModel)
    {new deleteAsyncTask(mDao).execute(newsModel);}

    private static class deleteAsyncTask extends AsyncTask<NewsModel,Void,Void>{

        private  NewsDao mAsyncTask;

        public deleteAsyncTask(NewsDao mDao){mAsyncTask=mDao;}
        @Override
        protected Void doInBackground(NewsModel... params) {
            mAsyncTask.deleteBookMark(params[0]);
            return null;
        }
    }

    MutableLiveData<List<NewsModel>> getmAllNews(String s){
        return apiService.getEverything(s);

    }
    MutableLiveData<List<NewsModel>> getmTopHeadline(){
        return apiService.getTopHeadlines();
    }

    MutableLiveData<List<NewsModel>> getmNewsByCategory(String c){
        return apiService.getByCategory(c);
    }

}
