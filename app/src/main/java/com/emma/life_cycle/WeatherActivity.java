package com.emma.life_cycle;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class WeatherActivity extends AppActivity {

    Button weatherButton;
    EditText weatherCity;
    TextView weatherView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        weatherButton = findViewById(R.id.weatherButton);
        weatherCity = findViewById(R.id.weatherCity);
        weatherView = findViewById(R.id.weatherView);

        weatherButton.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {

        // Variables
        String apiEndPoint = "https://api.openweathermap.org/data/2.5/weather?";
        String locationParam = "q=";
        String city = (String) weatherCity.getText().toString();
        String units = "units=metric";
        String apiKeyParam = "appid=";
        String paramDivider = "&";
        String apiKey = getString(R.string.openWeatherKey);



        // Build URL
        String urlString = apiEndPoint +
                locationParam +
                city +
                paramDivider +
                units +
                paramDivider +
                apiKeyParam +
                apiKey;


        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, urlString, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        // Set up String to display
                        String openWeatherResponse = "The weather in " + city + " is ";

                        try {
                            // Get weather-array and get description from it, then add response to openWeatherResponse-String
                            JSONArray weather = response.getJSONArray("weather");
                            JSONObject weatherJSONObject = weather.getJSONObject(0);
                            openWeatherResponse += weatherJSONObject.getString("description");

                            // Get main-object and get temp from it, then add response to openWeatherResponse-String
                            JSONObject main = response.getJSONObject("main");
                            String temp = main.getString("temp");
                            openWeatherResponse += " and the temperature is " + temp + "℃";

                            // Set openWeatherResponse-String to weatherView
                            weatherView.setText(openWeatherResponse);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                weatherView.setText("That didn't work!");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(jsonRequest);
    }
}