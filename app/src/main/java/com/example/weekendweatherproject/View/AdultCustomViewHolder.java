package com.example.weekendweatherproject.View;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weekendweatherproject.Model.DailyWeather;
import com.example.weekendweatherproject.R;

public class AdultCustomViewHolder extends RecyclerView.ViewHolder {
    private TextView title;
    private RecyclerView recyclerView;
    //TextView tv_clouds, tv_currentTemp, tv_zipCode;
    private Context context;
    public AdultCustomViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        title = itemView.findViewById(R.id.tv_date);
        recyclerView = itemView.findViewById(R.id.rv_child_recyclerView);

        //tv_clouds = itemView.findViewById(R.id.tv_clouds);
        //tv_currentTemp = itemView.findViewById(R.id.tv_currentTemp);
       // tv_zipCode = itemView.findViewById(R.id.tv_zipCode);

    }

    public void setDisplay(DailyWeather dailyWeather) {
        String[] dateStringSplit = dailyWeather.forecasts.get(0).dt_txt.split(" ");
        title.setText(dateStringSplit[0]);
        ChildCustomAdapter childCustomAdapter = new ChildCustomAdapter(context);
        recyclerView.setAdapter(childCustomAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 4));
        childCustomAdapter.setDataSet(dailyWeather);
    }
}
