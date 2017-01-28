package com.example.postsreader.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    public static HttpResponse connect(URL url) throws IOException{
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        Scanner scanner = new Scanner(connection.getInputStream());
        scanner.useDelimiter("\\A");
        StringBuilder responseBody = new StringBuilder();

        while (scanner.hasNext()){
            responseBody.append(scanner.next());
        }

        scanner.close();

        return new HttpResponse(responseBody.toString(), connection.getResponseCode());
    }
}
