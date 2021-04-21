package com.eldhose.newsapp.api;

import com.eldhose.newsapp.NewsModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JSONModel {

    @SerializedName("articles")
    public List<NewsModel> data ;

    public List<NewsModel> getData() {
        return data;
    }

}
