package com.example.postsreader.utils;

/**
 * Created by kamil_pc on 2017-01-28.
 */

public class HttpResponse {
    private String body;
    private int statusCode;

    public HttpResponse(String body, int statusCode) {
        this.body = body;
        this.statusCode = statusCode;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
