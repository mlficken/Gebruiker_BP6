package com.example.gebruiker_bp6.database;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class DatabaseConnection {

    private static final String BASE_URL = "https://protected-wave-28590.herokuapp.com/";
    private static AsyncHttpClient client = new AsyncHttpClient();

    // Maakt connectie met database
    public static void connect(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
        System.out.println(getAbsoluteUrl(url) + params);
    }

    // Maakt URL compleet met ingevoerde subnet
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
