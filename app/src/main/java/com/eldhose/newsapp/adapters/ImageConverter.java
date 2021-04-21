package com.eldhose.newsapp.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;


import com.eldhose.newsapp.NewsModel;

import com.eldhose.newsapp.room.NewsViewModel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

public class ImageConverter extends AsyncTask<NewsModel,Void,NewsModel> {

    private NewsViewModel newsViewModel;

    public ImageConverter(NewsViewModel newsViewModel){
        this.newsViewModel=newsViewModel;
    }

    @Override
    protected NewsModel doInBackground(NewsModel... newsModels) {
        URL newUrl;
        Bitmap bitmap;
        byte[] imageBytes;

        try {
            newUrl = new URL(newsModels[0].getImageUrl());
            bitmap = BitmapFactory.decodeStream(newUrl.openConnection().getInputStream());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,bos);
            imageBytes =bos.toByteArray();
            newsModels[0].setImage(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newsModels[0];
    }

    @Override
    protected void onPostExecute(NewsModel news) {

        newsViewModel.insertBookMark(news);
    }
}
