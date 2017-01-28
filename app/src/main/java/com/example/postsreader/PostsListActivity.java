package com.example.postsreader;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.postsreader.Api.ApiClient;
import com.example.postsreader.DTO.PostDTO;
import com.example.postsreader.adapters.PostsListAdapter;

import java.io.IOException;
import java.security.Policy;
import java.util.ArrayList;
import java.util.List;

public class PostsListActivity extends AppCompatActivity {
    private ApiClient apiClient;
    private RecyclerView recylerView;
    private List<PostDTO> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_list);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        apiClient = new ApiClient();

        recylerView = (RecyclerView) findViewById(R.id.rc_posts_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recylerView.setLayoutManager(layoutManager);

        posts = new ArrayList<>();

        try {
            posts = apiClient.getPosts();
        } catch (IOException ex) {
            Toast.makeText(this, "Cannot connecto to internet", Toast.LENGTH_LONG).show();
        }

        PostsListAdapter adapter = new PostsListAdapter(this, posts);

        recylerView.setAdapter(new PostsListAdapter(this, posts));
    }

    public void removeItem(int id){
        posts.remove(id);
    }
}
