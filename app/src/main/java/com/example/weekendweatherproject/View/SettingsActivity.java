package com.example.weekendweatherproject.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weekendweatherproject.R;

public class SettingsActivity  extends AppCompatActivity {
    EditText ab_editText;
    Button mainActivityReturn;
    String [] temp = {"Fahrenheit","Celsius"};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ab_editText = findViewById(R.id.btn_settings);
        mainActivityReturn = findViewById(R.id.btn_return);

        mainActivityReturn.setOnClickListener(v -> openMainActivity());

        //Spinner
        Spinner spin = findViewById(R.id.spinnerTemp);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, temp);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin.setAdapter(aa);
        //Spinner
    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
