package com.example.postsreader.Api;

public interface ResponseHandler<T> {
    void handleResponse(T response);
    void handleClientException();
    void handleServerException(int statusCode);
}
