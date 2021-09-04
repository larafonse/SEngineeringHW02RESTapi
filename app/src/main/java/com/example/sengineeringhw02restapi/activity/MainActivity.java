package com.example.sengineeringhw02restapi.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.text.method.ScrollingMovementMethod;

import com.example.sengineeringhw02restapi.R;
import com.example.sengineeringhw02restapi.model.Post;
import com.example.sengineeringhw02restapi.model.User;
import com.example.sengineeringhw02restapi.rest.PostApiService;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static Retrofit retrofit = null;
    private RecyclerView recyclerView = null;
    String post_text;
    TextView postTV;
    TextView usernameInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postTV = (TextView) findViewById(R.id.postsTV);
        usernameInfo = (TextView) findViewById(R.id.usernameInfo);

        connectAndGetApiData(LoginActivity.userLocation);
    }

    public void connectAndGetApiData(int userLocation) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }


        PostApiService postApiService = retrofit.create(PostApiService.class);

        Call<List<User>> userInfoCall = postApiService.getUserInfo(userLocation);

        userInfoCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                List<User> users = response.body();
                User u = users.get(0);
                usernameInfo.setText("Username :" + u.getUsername() + "\n User ID:"+ u.getId()+"\n\n");
                usernameInfo.setMovementMethod(new ScrollingMovementMethod());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("ERROR", t.toString());
            }
        });

        Call<List<Post>> postsCall = postApiService.getUserPosts(userLocation);

        postsCall.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> posts = response.body();
                post_text = "";
                for (Post post : posts) {
                    post_text = post_text + "ID :" + post.getId() + "\n";
                    post_text = post_text + "Title :" + post.getTitle() + "\n";
                    post_text = post_text + "Body : " + post.getBody() + "\n\n\n";
                }
                postTV.setText(post_text);
                postTV.setMovementMethod(new ScrollingMovementMethod());

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e("ERROR", t.toString());
            }
        });
    }
}