package com.eldhose.newsapp.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiDao {

    String BASE_URL = "https://newsapi.org/v2/";
    String API_KEY ="8727119d0e624ebfaa7c0cbe6b08beb4";

    @GET("everything?apiKey="+API_KEY)
    Call<JSONModel> getEverything(@Query("q") String search);

    @GET("top-headlines?country=in&apiKey="+API_KEY)
    Call<JSONModel> getTopHeadlines();

    @GET("top-headlines?apiKey="+API_KEY)
    Call<JSONModel> getByCategory(@Query("category") String category);

}
