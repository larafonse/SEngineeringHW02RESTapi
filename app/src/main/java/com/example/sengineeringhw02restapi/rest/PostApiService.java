package com.example.sengineeringhw02restapi.rest;

import com.example.sengineeringhw02restapi.model.Post;
import com.example.sengineeringhw02restapi.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PostApiService {
    @GET("/posts")
    Call<List<Post>> getUserPosts(@Query("userId")int id);

    @GET("users")
    Call<List<User>> getUserInfo(@Query("id") int id);
}
