package com.emma.life_cycle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;


public class SavedDataActivity extends AppActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_data);

        Intent intent = getIntent();
        Bundle savedDataBundle = intent.getBundleExtra("savedDataBundle");
        SharedPreferences sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);

        // Check if there is a bundle with data, if there is, save the data
        if (savedDataBundle != null) {
            String birthDate = savedDataBundle.getString("BirthDate");
            String bath = savedDataBundle.getString("Bath");
            String color = savedDataBundle.getString("Color");
            String dreams = savedDataBundle.getString("Dreams");
            String song = savedDataBundle.getString("Song");

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("You were born in ");
            stringBuilder.append(birthDate);
            stringBuilder.append(" and has bathed ");
            stringBuilder.append(bath);
            stringBuilder.append(" time / times this year. ");
            stringBuilder.append("Your favorite color is ");
            stringBuilder.append(color);
            stringBuilder.append(" and you had a dream last night about ");
            stringBuilder.append(dreams);
            stringBuilder.append(" and your favorite song is \"");
            stringBuilder.append(song);
            stringBuilder.append("\"");


            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("savedData", stringBuilder.toString());
            editor.apply();
        }

        // Get saved data or default text
        String savedUserData = sharedPreferences.getString("savedData", "No data is saved");
        textView = (TextView) findViewById(R.id.savedDataDisplay);
        textView.setText(savedUserData);
    }
}