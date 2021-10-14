package com.emma.life_cycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FormActivity extends AppActivity {

    Button saveButton;
    EditText formBirthDate;
    EditText formBath;
    EditText formColor;
    EditText formDreams;
    EditText formSong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        // Set up form
        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this::onClick);

        formBirthDate = (EditText) findViewById(R.id.formBirthDate);
        formBath = (EditText) findViewById(R.id.formBath);
        formColor = (EditText) findViewById(R.id.formColor);
        formDreams = (EditText) findViewById(R.id.formDreams);
        formSong = (EditText) findViewById(R.id.formSong);
    }


    private void onClick(View view) {

        // Save entered data onClick
        String inputBirthDate = formBirthDate.getText().toString();
        String inputBath = formBath.getText().toString();
        String inputColor = formColor.getText().toString();
        String inputDreams = formDreams.getText().toString();
        String inputSong = formSong.getText().toString();

        Bundle bundle = new Bundle();
        bundle.putString("BirthDate", inputBirthDate);
        bundle.putString("Bath", inputBath);
        bundle.putString("Color", inputColor);
        bundle.putString("Dreams", inputDreams);
        bundle.putString("Song", inputSong);

        Intent intent =  new Intent(this, SavedDataActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("savedDataBundle", bundle);
        startActivity(intent);
    }
}