package com.example.weekendweatherproject.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("forecast")
    Call<ForcastResultPojo> getWeekResults(@Query("zip") String zip,
                                           @Query("appid") String appId );

    //https://api.openweathermap.org/data/2.5/forecast?zip=30030&appid=9692a853bf2c0a8ca1e7d11af6f753c1

    @GET("weather")
    Call<CurrentResultsPojo> getCurrentResults(@Query("zip")String zip,
                                               @Query("appid")String appId);

    //https://api.openweathermap.org/data/2.5/weather?zip=30030,us&appid=9692a853bf2c0a8ca1e7d11af6f753c1
}

