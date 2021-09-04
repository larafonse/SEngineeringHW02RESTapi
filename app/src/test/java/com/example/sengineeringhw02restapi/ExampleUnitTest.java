package com.example.sengineeringhw02restapi;

import android.text.method.ScrollingMovementMethod;
import android.util.Log;

import com.example.sengineeringhw02restapi.activity.LoginActivity;
import com.example.sengineeringhw02restapi.model.Post;
import com.example.sengineeringhw02restapi.model.User;
import com.example.sengineeringhw02restapi.rest.PostApiService;

import org.junit.Test;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void checkInvalidUser() {
        String u = "Nick";
        boolean found = false;
        String[] loginNames = LoginActivity.generateUsers();
        for (String n :loginNames ){
            if(u.equals(n)){
                found = true;
            }
        }
        assertEquals(false, found);
    }

    @Test
    public void checkInvalidPassword() {

        String p = "notPassword";
        boolean isPassword = p.equals(LoginActivity.getPass());
        assertEquals(false, isPassword);

    }

    @Test
    public void checkUserApi(){
        Retrofit retrofit = null;
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        PostApiService postApiService = retrofit.create(PostApiService.class);

        Call<List<User>> userInfoCall = postApiService.getUserInfo(1);

        userInfoCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                assertTrue(response.body().size()>0);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                try {
                    throw t;
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });

    }

    @Test
    public void checkPostApi(){
        Retrofit retrofit = null;
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        PostApiService postApiService = retrofit.create(PostApiService.class);

        Call<List<Post>> postsCall = postApiService.getUserPosts(1);

        postsCall.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                assertTrue(response.body().size()>0);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                try {
                    throw t;
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });

    }

}