package com.example.postsreader.Api;

import com.example.postsreader.DTO.CommentDTO;
import com.example.postsreader.DTO.PostDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.IOException;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class ApiClient {
    private AsyncHttpClient client = new AsyncHttpClient();
    private static final String HOST = "http://jsonplaceholder.typicode.com";
    private ObjectMapper mapper;

    public ApiClient() {
        this.mapper = new ObjectMapper();
    }

    public void getPosts(final ResponseHandler<List<PostDTO>> responseHandler) {
        String path = "/posts";
        sendGet(HOST + path, null, new TypeReference<List<PostDTO>>() {}, responseHandler);
    }

    public void getPost(int postId, final ResponseHandler<PostDTO> responseHandler) {
        String path = "/posts/" + postId;
        sendGet(HOST + path, null, new TypeReference<PostDTO>() {}, responseHandler);
    }

    public void getComments(int postId, final ResponseHandler<List<CommentDTO>> responseHandler) {
        String path = "/posts/" + postId + "/comments";
        sendGet(HOST + path, null, new TypeReference<List<CommentDTO>>() {}, responseHandler);
    }

    private void sendGet(String url,
                         RequestParams params,
                         final TypeReference type,
                         final ResponseHandler responseHandler) {
        client.get(url, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode,
                                  Header[] headers,
                                  String responseString,
                                  Throwable throwable) {
                responseHandler.handleError(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                try {
                    Object posts = mapper.readValue(responseString, type);
                    responseHandler.handleResponse(posts);
                } catch (IOException e) {
                    onFailure(-1, null, "", e);
                }
            }

            @Override
            public void onStart() {
                responseHandler.beforeRequest();
            }
        });
    }
}
