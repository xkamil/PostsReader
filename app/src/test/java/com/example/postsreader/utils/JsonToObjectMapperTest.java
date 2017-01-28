package com.example.postsreader.utils;

import com.example.postsreader.DTO.PostDTO;

import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JsonToObjectMapperTest {

    private JsonToObjectMapper mapper;

    @Before
    public void beforeTest(){
        mapper = new JsonToObjectMapper();
    }

    @Test
    public void testMapPost() throws Exception {

        PostDTO expectedPost = new PostDTO(1, 1, "some title", "some body");

        String postJson = "{\n" +
                "  \"userId\": 1,\n" +
                "  \"id\": 1,\n" +
                "  \"title\": \"some title\",\n" +
                "  \"body\": \"some body\" \n" +
                "}";

        PostDTO post = mapper.mapPost(postJson);

        assertEquals(expectedPost, post);
    }

    @Test
    public void testMapPostList() throws Exception {

        PostDTO post1 = new PostDTO(1, 1, "some title", "some body");
        PostDTO post2 = new PostDTO(2, 2, "some title2", "some body2");
        List<PostDTO> expectedPostList = new ArrayList<>();
        expectedPostList.add(post1);
        expectedPostList.add(post2);

        String json1 = "{\n" +
                "  \"userId\": 1,\n" +
                "  \"id\": 1,\n" +
                "  \"title\": \"some title\",\n" +
                "  \"body\": \"some body\" \n" +
                "}";

        String json2 = "{\n" +
                "  \"userId\": 2,\n" +
                "  \"id\": 2,\n" +
                "  \"title\": \"some title2\",\n" +
                "  \"body\": \"some body2\" \n" +
                "}";
        String json3 = "[" + json1 + "," + json2 + "]";


        List<PostDTO> postList = mapper.mapPosts(json3);

        assertEquals(expectedPostList, postList);

    }

}