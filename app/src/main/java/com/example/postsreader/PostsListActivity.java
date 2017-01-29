package com.example.postsreader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.postsreader.Api.ApiClient;
import com.example.postsreader.Api.ResponseHandler;
import com.example.postsreader.DTO.PostDTO;
import com.example.postsreader.adapters.ClickListener;
import com.example.postsreader.adapters.PostsListAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PostsListActivity extends AppCompatActivity implements ClickListener {
    private RecyclerView recylerView;
    private List<PostDTO> posts;
    private PostsListAdapter adapter;
    private View progressBar;
    private ApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_list);

        client = new ApiClient();

        progressBar = findViewById(R.id.progress_bar);

        recylerView = (RecyclerView) findViewById(R.id.rc_posts_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recylerView.setLayoutManager(layoutManager);

        posts = Collections.synchronizedList(new ArrayList<PostDTO>());

        adapter = new PostsListAdapter(this, posts, this);

        recylerView.setAdapter(new PostsListAdapter(this, posts, this));

        client.getPosts(new ResponseHandler<List<PostDTO>>() {
            @Override
            public void handleResponse(List<PostDTO> responsePosts) {
                posts.addAll(responsePosts);
                adapter.notifyDataSetChanged();
                recylerView.swapAdapter(adapter, true);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void handleError(int error) {
                Log.e("ERROR", "client exception");
            }

            @Override
            public void beforeRequest() {
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onClick(Object post) {
        Intent intent = new Intent(this, PostActivity.class);
        intent.putExtra("postId", ((PostDTO)post).getId());

        startActivity(intent);

        //posts.remove(post);
        //adapter.notifyDataSetChanged();
    }
}
