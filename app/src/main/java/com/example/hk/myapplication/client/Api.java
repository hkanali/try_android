package com.example.hk.myapplication.client;

import com.example.hk.myapplication.client.entity.News;
import com.example.hk.myapplication.client.entity.PageImpl;
import com.example.hk.myapplication.client.entity.PlayerPost;
import com.example.hk.myapplication.client.entity.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("/api/user/me")
    Call<User> getUser();

    @GET("/api/news")
    Call<News> getNews(@Query("id") Long id);

    @GET("/api/post")
    Call<PageImpl<PlayerPost>> getPosts(@Query("page") int page);
}
