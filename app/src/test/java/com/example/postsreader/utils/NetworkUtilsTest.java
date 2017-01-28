package com.example.postsreader.utils;


import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.net.URL;
import java.net.UnknownHostException;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.junit.Assert.assertEquals;

public class NetworkUtilsTest {

    private static String host = "https://jsonplaceholder.typicode.com";

    @Test
    public void testConnect() throws Exception {
        URL url = new URL(host + "/todos/1");

        String expectedResponse = "{\n" +
                "  \"userId\": 1,\n" +
                "  \"id\": 1,\n" +
                "  \"title\": \"delectus aut autem\",\n" +
                "  \"completed\": false\n" +
                "}";

        HttpResponse response = NetworkUtils.connect(url);

        JSONAssert.assertEquals(expectedResponse, response.getBody(), true);
        assertEquals(HTTP_OK, response.getStatusCode());

    }

    @Test(expected = UnknownHostException.class)
    public void testConnectToNonExistingHost() throws Exception {
        URL url = new URL(host + ".pl");
        HttpResponse response = NetworkUtils.connect(url);
    }
}