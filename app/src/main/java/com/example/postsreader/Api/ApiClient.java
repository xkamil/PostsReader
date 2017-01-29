package com.example.postsreader.Api;

import android.os.AsyncTask;

import com.example.postsreader.DTO.PostDTO;
import com.example.postsreader.utils.HttpResponse;
import com.example.postsreader.utils.JsonToObjectMapper;
import com.example.postsreader.utils.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class ApiClient {
    private static final String HOST = "https://jsonplaceholder.typicode.com";
    private JsonToObjectMapper mapper;

    public ApiClient() {
        this.mapper = new JsonToObjectMapper();
    }

    public void getPosts(final ResponseHandler<List<PostDTO>> responseHandler) {
        new AsyncTask<Void, Void, List<PostDTO>>() {
            @Override
            protected List<PostDTO> doInBackground(Void... params) {
                try {
                    HttpResponse response = getResponse("/posts");
                    return mapper.mapPosts(response.getBody());

                } catch (IOException ex) {
                    responseHandler.handleClientException();
                }
                return null;
            }

            @Override
            protected void onPostExecute(List<PostDTO> posts) {
                responseHandler.handleResponse(posts);
            }
        }.execute();
    }

    public void getPost(final int id, final ResponseHandler<PostDTO> responseHandler) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    HttpResponse response = getResponse("/posts/" + id);
                    PostDTO post = mapper.mapPost(response.getBody());
                    responseHandler.handleResponse(post);
                } catch (IOException ex) {
                    responseHandler.handleClientException();
                }
                return null;
            }
        }.execute();
    }

    private HttpResponse getResponse(String path) throws IOException {
        URL url = new URL(HOST + path);
        return NetworkUtils.connect(url);
    }
}
