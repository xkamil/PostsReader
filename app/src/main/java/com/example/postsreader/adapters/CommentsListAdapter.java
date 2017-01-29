package com.example.postsreader.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.postsreader.DTO.CommentDTO;
import com.example.postsreader.R;

import java.util.List;

public class CommentsListAdapter extends RecyclerView.Adapter<CommentsListAdapter.CommentViewHandler>{

    private List<CommentDTO> comments;
    private Context context;
    private LayoutInflater inflater;

    public CommentsListAdapter(Context context, List<CommentDTO> comments) {
        this.comments = comments;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public CommentViewHandler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.comment_item, parent, false);
        return new CommentViewHandler(view);
    }

    @Override
    public void onBindViewHolder(CommentViewHandler holder, int position) {
        holder.bind(comments.get(position));
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class CommentViewHandler extends RecyclerView.ViewHolder{

        private TextView name;
        private TextView email;
        private TextView body;

        public CommentViewHandler(View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.comment_name);
            email = (TextView)itemView.findViewById(R.id.comment_email);
            body = (TextView)itemView.findViewById(R.id.comment_body);
        }

        public void bind(CommentDTO comment){
            name.setText(comment.getName());
            email.setText(comment.getEmail());
            body.setText(comment.getBody());
        }
    }
}
