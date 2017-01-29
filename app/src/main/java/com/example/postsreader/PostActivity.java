package com.example.postsreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.postsreader.Api.ApiClient;
import com.example.postsreader.Api.ResponseHandler;
import com.example.postsreader.DTO.CommentDTO;
import com.example.postsreader.DTO.PostDTO;
import com.example.postsreader.adapters.CommentsListAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PostActivity extends AppCompatActivity {
    private ApiClient client;
    private TextView title;
    private TextView body;
    private TextView commentsHeader;

    private RecyclerView recyclerView;
    private CommentsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        final List<CommentDTO> comments = Collections.synchronizedList(new ArrayList<CommentDTO>());

        recyclerView = (RecyclerView)findViewById(R.id.rc_comments_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);

        adapter = new CommentsListAdapter(this, comments);

        recyclerView.setAdapter(adapter);

        this.title = (TextView) findViewById(R.id.post_title);
        this.body = (TextView) findViewById(R.id.post_body);
        this.commentsHeader = (TextView) findViewById(R.id.comments_header);

        client = new ApiClient();
        int postId = getIntent().getIntExtra("postId", -1);

        client.getPost(postId, new ResponseHandler<PostDTO>() {
            @Override
            public void handleResponse(PostDTO response) {
                title.setText(response.getTitle());
                body.setText(response.getBody());
            }
        });

        client.getComments(postId, new ResponseHandler<List<CommentDTO>>() {
            @Override
            public void handleResponse(List<CommentDTO> response) {
                commentsHeader.setText("Komentarze(" + response.size() + ")");
                comments.addAll(response);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
