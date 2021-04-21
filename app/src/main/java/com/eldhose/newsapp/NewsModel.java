package com.eldhose.newsapp;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "news_table")
public class NewsModel implements Serializable {

    @SerializedName("author")
    @ColumnInfo(name ="author")
    String author;

    @PrimaryKey
    @NonNull
    @SerializedName("title")
    @ColumnInfo(name = "title")
    String title;

    @SerializedName("description")
    @ColumnInfo(name = "description")
    String description;

    @SerializedName("urlToImage")
    @ColumnInfo(name = "urlToImage")
    String imageUrl;

    @SerializedName("publishedAt")
    @ColumnInfo(name = "publishedAt")
    String time;

    @SerializedName("content")
    @ColumnInfo(name = "content")
    String content;

    @ColumnInfo( name = "image",typeAffinity = ColumnInfo.BLOB)
    private byte[] image;

    public NewsModel(NewsModel newsModel) {
        author=newsModel.author;
        title=newsModel.title;
        description=newsModel.description;
        imageUrl=newsModel.imageUrl;
        time=newsModel.time;
        content=newsModel.content;
    }

    public NewsModel(String author, String title, String description, String imageUrl, String time, String content) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.time = time;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
