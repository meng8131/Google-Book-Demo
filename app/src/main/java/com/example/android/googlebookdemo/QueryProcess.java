package com.example.android.googlebookdemo;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyman on 2017/8/16.
 */

public class QueryProcess {
    private static final String LOG_TAG = QueryProcess.class.getName();

    /**
     * Create a private constructor because no one should ever create a {@link QueryProcess} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryProcess (and an object instance of QueryUtils is not needed).
     */

    private QueryProcess(){
    }

    public static List<BookInfo> fetchBookData(String requestUrl){
        URL url = createUrl(requestUrl);

        String jsonResponse =null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e){
            Log.e(LOG_TAG, "Problem making the HTTP request",e);
        }
        List<BookInfo> books = extractFromJson(jsonResponse);

        return books;
    }


    private static URL createUrl(String stringUrl){
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e){
            Log.e(LOG_TAG, "Problem with building URL", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException{
        String jsonResponse = "";
        if (url == null ){
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();


            if (urlConnection.getResponseCode() == 200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromSteam(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code" + urlConnection.getResponseCode());
            }
        } catch (IOException e){
            Log.e(LOG_TAG, "Problem retrieving the JSON results", e);
        } finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
            if (inputStream != null){
                inputStream.close();
            }
        }
        
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromSteam(InputStream inputStream) throws IOException{
        StringBuilder output = new StringBuilder();
        if (inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line != null){
                output.append(line);
                line = bufferedReader.readLine();
            }
        }
        return output.toString();
    }

    private static List<BookInfo> extractFromJson(String responseJson) {
        if (TextUtils.isEmpty(responseJson)){
            return null;
        }

        List<BookInfo> books = new ArrayList<>();

        try {
            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(responseJson);
            JSONArray itemsArray = baseJsonResponse.getJSONArray("items");
            for (int i=0; i < itemsArray.length(); i++) {
                JSONObject currentBook = itemsArray.getJSONObject(i);
                JSONObject volumeInfo = currentBook.getJSONObject("volumeInfo");
                String title = volumeInfo.getString("title");

                JSONArray authors =null;
                String firstAuthor =null;
                //only fetch the first author
                if (volumeInfo.getJSONArray("authors") != null){
                    authors = volumeInfo.getJSONArray("authors");
                    firstAuthor = authors.getString(0);
                }

                BookInfo book = new BookInfo(title, firstAuthor);
                books.add(book);
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing JSON results", e);
        }
        return  books;
    }
}
