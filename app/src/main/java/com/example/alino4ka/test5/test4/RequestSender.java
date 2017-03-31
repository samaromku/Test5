package com.example.alino4ka.test5.test4;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class RequestSender {
    public static final String API_KEY = "a7f2dbeae9bdcbc464399fd84113ae68";
    public static final String URL = "http://api.openweathermap.org/data/2.5/weather";
    public static final String TAG = "RequestSender";

    public Weather sendRequest(String lat, String lon){
        String url = Uri.parse(URL)
                .buildUpon()
                .appendQueryParameter("lat", lat)
                .appendQueryParameter("lon", lon)
                .appendQueryParameter("APPID", API_KEY)
                .build().toString();
        try {
            String json = getUrlString(url);
            Log.i(TAG, "sendRequest: " + json);
            JSONObject jsonObject = new JSONObject(json);
            return parseItems(jsonObject);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }


    public byte[] getUrlBytes(String urlSpec) throws IOException {
        java.net.URL url = new URL(urlSpec);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try{
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = urlConnection.getInputStream();

            if(urlConnection.getResponseCode()!=HttpURLConnection.HTTP_OK){
                throw new IOException(urlConnection.getResponseMessage() + " " + urlSpec);
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer))>0){
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        }finally {
            urlConnection.disconnect();
        }
    }

    private Weather parseItems(JSONObject jsonObject) throws JSONException {
        JSONObject main = jsonObject.getJSONObject("main");
        JSONObject wind = jsonObject.getJSONObject("wind");
        String tempMin = main.getString("temp_min");
        String tempMax = main.getString("temp_max");
        String pressure = main.getString("pressure");
        String humidity = main.getString("humidity");
        String speed = wind.getString("speed");
        String dir = wind.getString("deg");
        String clouds = jsonObject.getJSONObject("clouds").getString("all");
        String temp = tempMin + " - " + tempMax;

        Weather weather = new Weather(temp, pressure, humidity, dir, speed, clouds);
        Log.i(TAG, "parseItems: " + temp+pressure+humidity+dir + speed + clouds);
        return weather;
    }
}
