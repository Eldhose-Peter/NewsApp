package com.eldhose.newsapp.api;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.eldhose.newsapp.NewsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    Context context;
    ApiDao api;
    MutableLiveData <List<NewsModel>> mutableLiveData =new MutableLiveData<>();
    List<NewsModel> newsModelList;


    public ApiService(Context context) {
        this.context=context;
        createRetrofit();
    }

    //Using retrofit
    public void createRetrofit() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiDao.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        api = retrofit.create(ApiDao.class);
    }

    public MutableLiveData<List<NewsModel>> getTopHeadlines()
    {
        Call<JSONModel> call = api.getTopHeadlines();
        call.enqueue(new Callback<JSONModel>() {
            @Override
            public void onResponse(Call<JSONModel> call, Response<JSONModel> response) {
                JSONModel JSONNewsList = response.body();

                newsModelList = JSONNewsList.getData();

                mutableLiveData.postValue(newsModelList);
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<JSONModel> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<NewsModel>> getEverything(String search)
    {
        Call<JSONModel> call = api.getEverything(search);
        call.enqueue(new Callback<JSONModel>() {
            @Override
            public void onResponse(Call<JSONModel> call, Response<JSONModel> response) {
                JSONModel JSONNewsList = response.body();

                mutableLiveData.setValue(JSONNewsList.getData());
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<JSONModel> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<NewsModel>> getByCategory(String category)
    {
        Call<JSONModel> call = api.getByCategory(category);
        call.enqueue(new Callback<JSONModel>() {
            @Override
            public void onResponse(Call<JSONModel> call, Response<JSONModel> response) {
                JSONModel JSONNewsList = response.body();

                mutableLiveData.postValue(JSONNewsList.getData());
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<JSONModel> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        return mutableLiveData;

    }


  // Saves a bitmap of the image url in the object
  /*  private static class convertImage extends AsyncTask<NewsModel,Void,Void>{
        @Override
        protected Void doInBackground(NewsModel... newsModels) {
            URL newUrl;
            Bitmap bitmap = null;
            byte[] imageBytes = new byte[0];

            try {
                newUrl = new URL(newsModels[0].getImageUrl());
                bitmap = BitmapFactory.decodeStream(newUrl.openConnection().getInputStream());
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,bos);
                imageBytes =bos.toByteArray();
                /*img = Base64.encodeToString(imageBytes,Base64.);
            } catch (IOException e) {
                e.printStackTrace();
            }

            newsModels[0].setImage(imageBytes);
            return null;
        }
    }*/


}
