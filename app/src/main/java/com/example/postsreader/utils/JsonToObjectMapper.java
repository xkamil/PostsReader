package com.example.postsreader.utils;

import com.example.postsreader.DTO.PostDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * Created by kamil_pc on 2017-01-28.
 */

public class  JsonToObjectMapper{
    private ObjectMapper mapper;

    public JsonToObjectMapper(){
        mapper = new ObjectMapper();
    }

    public List<PostDTO> mapPosts(String json) throws IOException{
        return mapper.readValue(json, new TypeReference<List<PostDTO>>(){});
    }

    public PostDTO mapPost(String json) throws IOException{
        return mapper.readValue(json, PostDTO.class);
    }
}
