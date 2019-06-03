package com.example.weekendweatherproject.View;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.weekendweatherproject.Model.ApiInterface;
import com.example.weekendweatherproject.Model.CurrentResultsPojo;
import com.example.weekendweatherproject.Model.ForcastResultPojo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomViewModel {
    private static final String TAG = "CustomViewModel";
    private static final String APPID = "9692a853bf2c0a8ca1e7d11af6f753c1";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    private static ApiInterface api;

    private String zip = "30030";

    private MutableLiveData<ForcastResultPojo> forecastDataset = new MutableLiveData<>();

    public CustomViewModel() {
        initRetrofit();
    }

    public ApiInterface initRetrofit() {
        api = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface.class);
        return api;
    }


    public LiveData<ForcastResultPojo> getForcast() {
        api.getWeekResults(zip, APPID).enqueue(new Callback<ForcastResultPojo>() {
            @Override
            public void onResponse(Call<ForcastResultPojo> call, Response<ForcastResultPojo> response) {
                forecastDataset.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ForcastResultPojo> call, Throwable t) {
                Log.d(TAG, "onFailure: forecast failed");
            }
        });
        return forecastDataset;
    }

    public LiveData<CurrentResultsPojo> getLiveWeather() {
        MutableLiveData<CurrentResultsPojo> currentWeatherDataSet = new MutableLiveData<>();
        api.getCurrentResults(zip, APPID).enqueue(new Callback<CurrentResultsPojo>() {
            @Override
            public void onResponse(Call<CurrentResultsPojo> call, Response<CurrentResultsPojo> response) {
                currentWeatherDataSet.setValue(response.body());
            }

            @Override
            public void onFailure(Call<CurrentResultsPojo> call, Throwable t) {
                Log.d(TAG, "onFailure: current weather failed");
            }
        });
        return currentWeatherDataSet;

    }
}