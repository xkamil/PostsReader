package com.example.postsreader.Api;

import android.content.res.Resources;
import android.util.Log;

import com.example.postsreader.DTO.PostDTO;
import com.example.postsreader.utils.HttpResponse;
import com.example.postsreader.utils.JsonToObjectMapper;
import com.example.postsreader.utils.NetworkUtils;
import com.example.postsreader.utils.NotFoundException;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static java.net.HttpURLConnection.HTTP_NOT_FOUND;

public class ApiClient {
    private static String TAG = ApiClient.class.getSimpleName();
    private static String HOST = "https://jsonplaceholder.typicode.com";
    private JsonToObjectMapper mapper;

    public ApiClient() {
        this.mapper = new JsonToObjectMapper();
    }

    public List<PostDTO> getPosts() throws IOException{
            HttpResponse response = getResponse("/posts");
            return mapper.mapPosts(response.getBody());
    }

    public PostDTO getPost(int id) throws IOException, NotFoundException {
        HttpResponse response = getResponse("/posts/" + id);

        if (response.getStatusCode() == HTTP_NOT_FOUND){
            throw new NotFoundException();
        }

        return mapper.mapPost(response.getBody());
    }

    private HttpResponse getResponse(String path) throws IOException {
        URL url = new URL(HOST + path);
        return NetworkUtils.connect(url);
    }
}
