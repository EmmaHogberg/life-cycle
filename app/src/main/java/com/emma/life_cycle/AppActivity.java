package com.emma.life_cycle;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AppActivity extends AppCompatActivity {

    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.overflow_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        SharedPreferences sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("id", "false");

        // Redirect to chosen activity
        switch (item.getItemId()) {

            case R.id.menuStart:
                Intent intentStart = new Intent(this, MainActivity.class);
                intentStart.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentStart);
                finish();
                return true;

            case R.id.menuWeather:
                    Intent intentWeather = new Intent(this, WeatherActivity.class);
                    intentWeather.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intentWeather);
                    finish();
                    return true;

            case R.id.menuForm:
                if (!id.equals("false")) {
                    Intent intentForm = new Intent(this, FormActivity.class);
                    intentForm.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intentForm);
                    finish();
                    return true;
                }

            case R.id.menuSaved:
                if (!id.equals("false")) {
                    Intent intentSaved = new Intent(this, SavedDataActivity.class);
                    intentSaved.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intentSaved);
                    finish();
                    return true;
                }

            case R.id.menuLogout:
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(this,"Logged out",Toast.LENGTH_SHORT).show();

                Intent intentLogout = new Intent(this, MainActivity.class);
                intentLogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentLogout);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
