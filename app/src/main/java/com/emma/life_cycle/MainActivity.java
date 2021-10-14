package com.emma.life_cycle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppActivity {

    EditText mainNameInput;
    EditText mainPasswordInput;
    Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Login items
        mainNameInput = (EditText) findViewById(R.id.mainNameInput);
        mainPasswordInput = (EditText) findViewById(R.id.mainPasswordInput);
        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this::onClick);

        // Check if user is logged in already
        SharedPreferences sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("id", "false");

        if (!id.equals("false")) {
            Intent intent = new Intent(this, FormActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }



    public void onClick(View view) {

        String nameInput = mainNameInput.getText().toString();
        String passwordInput = mainPasswordInput.getText().toString();
        Log.d("id-emma", nameInput + " " + passwordInput);

        // Assign ID if the correct password is entered
        if (!nameInput.isEmpty() && !passwordInput.isEmpty()) {

            String id = UniqueID.getID(nameInput, passwordInput);
            Log.d("id-emma", id);

            // Login if the is a ID and set ID to sharedPreferences
            if (!id.isEmpty()) {
                SharedPreferences sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("id", id);
                editor.apply();
                Log.d("id-emma", "SharedPref");

                Intent intent = new Intent(this, FormActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }
    }
}