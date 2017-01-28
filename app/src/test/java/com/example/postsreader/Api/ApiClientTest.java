package com.example.postsreader.Api;


import com.example.postsreader.DTO.PostDTO;
import com.example.postsreader.utils.HttpResponse;
import com.example.postsreader.utils.NetworkUtils;

import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ApiClientTest {

    private static String host = "https://jsonplaceholder.typicode.com";

    @Test
    public void testGetPosts() throws Exception {
        ApiClient api = new ApiClient();

        List<PostDTO> posts = api.getPosts();

        assertEquals(100, posts.size());
    }

    @Test
    public void testGetPost() throws Exception {
        ApiClient api = new ApiClient();

        PostDTO post = api.getPost(1);

        assertEquals("sunt aut facere repellat provident occaecati excepturi optio reprehenderit", post.getTitle());
        assertEquals("quia et suscipit\n" +
                "suscipit recusandae consequuntur expedita et cum\n" +
                "reprehenderit molestiae ut ut quas totam\n" +
                "nostrum rerum est autem sunt rem eveniet architecto", post.getBody());
        assertEquals(1, post.getId());
        assertEquals(1, post.getUserId());
    }

}