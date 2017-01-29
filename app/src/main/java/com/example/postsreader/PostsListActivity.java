package com.example.postsreader;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.example.postsreader.Api.ApiClient;
import com.example.postsreader.Api.ResponseHandler;
import com.example.postsreader.DTO.PostDTO;
import com.example.postsreader.adapters.PostsListAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PostsListActivity extends AppCompatActivity {
    private  RecyclerView recylerView;
    private  List<PostDTO> posts;
    private  PostsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_list);

        recylerView = (RecyclerView) findViewById(R.id.rc_posts_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recylerView.setLayoutManager(layoutManager);

        posts = Collections.synchronizedList(new ArrayList<PostDTO>());

        adapter = new PostsListAdapter(this, posts);
        recylerView.setAdapter(new PostsListAdapter(this, posts));

        new ApiClient().getPosts(new ResponseHandler<List<PostDTO>>() {
            @Override
            public void handleResponse(List<PostDTO> responsePosts) {
                posts.addAll(responsePosts);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void handleClientException() {
                Log.e("ERROR", "client exception");
            }

            @Override
            public void handleServerException(int stautsCode) {
                Log.e("ERROR", "server exception");
            }
        });
    }
}
