package com.example.postsreader.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.postsreader.DTO.PostDTO;
import com.example.postsreader.PostsListActivity;
import com.example.postsreader.R;

import java.util.List;

public class PostsListAdapter extends RecyclerView.Adapter<PostsListAdapter.PostViewHolder>{

    private List<PostDTO> posts;
    private LayoutInflater inflater;
    private Context context;

    public PostsListAdapter(Context context, List<PostDTO> posts){
        this.posts = posts;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.post_item, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        holder.bind(posts.get(position));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView body;
        private View container;

        public PostViewHolder(View itemView) {
            super(itemView);
            this.container = itemView;
            title = (TextView) itemView.findViewById(R.id.post_title);
            body = (TextView) itemView.findViewById(R.id.post_body);
        }

        public void bind(final PostDTO post){
            title.setText(post.getTitle());
            body.setText(post.getBody());
        }
    }
}
