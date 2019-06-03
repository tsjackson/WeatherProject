package com.example.weekendweatherproject.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weekendweatherproject.Model.CurrentResultsPojo;
import com.example.weekendweatherproject.Model.DailyWeather;
import com.example.weekendweatherproject.Model.ForcastResultPojo;
import com.example.weekendweatherproject.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    CustomViewModel customViewModel;
    AdultCustomAdapter adAdapter;
    RecyclerView adultRecyclerView;
    TextView tv_currentTemp, tv_zipCode,tv_clouds;
    FloatingActionButton settings;
    DecimalFormat numberFormat = new DecimalFormat("###.#");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_currentTemp = findViewById(R.id.tv_currentTemp);
        tv_zipCode = findViewById(R.id.tv_zipCode);
        tv_clouds = findViewById(R.id.tv_clouds);

        settings = findViewById(R.id.btn_settings);

        customViewModel = new CustomViewModel();

        adultRecyclerView = findViewById(R.id.rv_adult_recyclerView);
        adultRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adAdapter = new AdultCustomAdapter();
        adultRecyclerView.setAdapter(adAdapter);
        TextView tv_currentTemp,tv_zipCode,tv_clouds;

//        childRecyclerView = findViewById(R.id.rv_child_recyclerView);
//        chAdapter = new ChildCustomAdapter(this);
        customViewModel.getForcast().observe(this,
                this::processForecast);
        customViewModel.getLiveWeather().observe(this,
                this::getCurrentTemp);
        settings.setOnClickListener(v -> openSettings());
    }

    private void openSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void getCurrentTemp(CurrentResultsPojo currentWeather) {
        String temp = currentWeather.main.temp;
        String zip = currentWeather.name;
        String clouds = currentWeather.weather.get(0).main;

        double farenheitTemp = (9.0 / 5) * (Double.valueOf(temp) - 273) + 32;


        tv_currentTemp.setText(Double.toString(Double.parseDouble(numberFormat.format(farenheitTemp))));
        tv_zipCode.setText(zip);
        tv_clouds.setText(clouds);

    }

    private void processForecast(ForcastResultPojo pojo) {
        if (pojo != null) {
            List<DailyWeather> dayList = new ArrayList<>();
            DailyWeather day = new DailyWeather();
            String date = null;
            for (int i = 0; i < pojo.list.size(); i++) {
                if (date == null) {
                    String[] splitDateTime = pojo.list.get(i).dt_txt.split(" ");
                    date = splitDateTime[0];
                    day.forecasts.add(pojo.list.get(i));
                } else {
                    String[] splitDateTime = pojo.list.get(i).dt_txt.split(" ");
                    String newDate = splitDateTime[0];
                    if (newDate.equals(date)) {
                        //if newdate matches date
                        //      add to day forecast
                        day.forecasts.add(pojo.list.get(i));
                    } else {
                        date = newDate;
                        dayList.add(day);
                        day = new DailyWeather();
                        day.forecasts.add(pojo.list.get(i));
                        // else
                        //      add day to list
                        //      set date to null
                        //      reinitialise day
                    }
                }
            }
            getResultAdult(dayList);
        }
    }
    public void getResultAdult(List<DailyWeather> dailyWeather) {
        adAdapter.setDataSet(dailyWeather);
    }

    public void getResultChild(DailyWeather dailyWeather) {
//        chAdapter.setDataSet(dailyWeather);
    }
}
