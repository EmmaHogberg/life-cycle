package com.emma.life_cycle;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Är det rätt att extenda denna?
public class OpenWeather {

    public void getWeather(String city, String apiKey, Context context) {

        // Variables
        String apiEndPoint = "http://api.openweathermap.org/data/2.5/weather?";
        String locationParam = "q=";
        String units = "units=metric";
        String apiKeyParam = "appid=";
        String paramDivider = "&";

        // Build URL
        String urlString = apiEndPoint +
                locationParam +
                city +
                paramDivider +
                units +
                paramDivider +
                apiKeyParam +
                apiKey;


        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, urlString, null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.d("volley", "onResponse: " + response.toString());

                try {
                    JSONArray weather = response.getJSONArray("weather");
                    Log.d("volley", "onResponse: " + weather.toString());
                    for (int i = 0; i < weather.length(); i++) {

                        JSONObject weatherJSONObject = weather.getJSONObject(i); // its only 0 long
                        Log.d("volley", "onResponse: " +  weatherJSONObject.getString("description"));
                        //cloudLimit.setText(weatherJSONObject.getString("description"));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //cloudLimit.setText("That didn't work!");
                Log.d("volley", "onErrorResponse: " + error);
            }
        });

        // Add the request to the RequestQueue.
        queue.add(jsonRequest);

    }

}
