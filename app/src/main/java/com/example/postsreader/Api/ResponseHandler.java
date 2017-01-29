package com.example.postsreader.Api;

public abstract class ResponseHandler<T> {
        public abstract void handleResponse(T response);
        public void handleError(int error){};
        public void beforeRequest(){}
}
