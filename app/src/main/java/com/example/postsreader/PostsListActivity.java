package com.example.postsreader;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.postsreader.Api.ApiClient;
import com.example.postsreader.DTO.PostDTO;
import com.example.postsreader.adapters.PostsListAdapter;

import java.io.IOException;
import java.security.Policy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PostsListActivity extends AppCompatActivity {
    private ApiClient apiClient;
    private RecyclerView recylerView;
    private List<PostDTO> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_list);

        apiClient = new ApiClient();

        recylerView = (RecyclerView) findViewById(R.id.rc_posts_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recylerView.setLayoutManager(layoutManager);


        posts = Collections.synchronizedList(new ArrayList<PostDTO>());

        //posts = new ArrayList<>();

        PostsListAdapter adapter = new PostsListAdapter(this, posts);
        recylerView.setAdapter(new PostsListAdapter(this, posts));

        new DownloadDataAsync(new DownloadAndFetchPostsCommand(adapter, posts)).execute();
    }

    class DownloadAndFetchPostsCommand implements Command{
        private RecyclerView.Adapter adapter;
        private List<PostDTO> posts;


        public DownloadAndFetchPostsCommand(RecyclerView.Adapter adapter, List<PostDTO> posts){
            this.adapter = adapter;
            this.posts = posts;
        }

        @Override
        public void execute() {
            try {
                this.posts.addAll(apiClient.getPosts());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void fetchResult() {
            Log.i("DUPA", "Items:" + posts.size());
            Log.i("DUPA", "In adapter:" + adapter.getItemCount());
            adapter.notifyDataSetChanged();
        }
    }


    class DownloadDataAsync extends AsyncTask<Void, Void, Void> {
        private Command command;

        public DownloadDataAsync(Command command){
            this.command = command;
        }

        @Override
        protected Void doInBackground(Void... params) {
            command.execute();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            command.fetchResult();
        }
    }

    interface Command {
        void execute();
        void fetchResult();
    }
}
