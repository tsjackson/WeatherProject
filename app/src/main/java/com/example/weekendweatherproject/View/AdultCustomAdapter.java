package com.example.weekendweatherproject.View;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weekendweatherproject.Model.DailyWeather;
import com.example.weekendweatherproject.R;

import java.util.ArrayList;
import java.util.List;

public class AdultCustomAdapter extends RecyclerView.Adapter<AdultCustomViewHolder> {
    private List<DailyWeather> dataSet = new ArrayList<>();

    private static final String TAG = "AdultCustomAdapter";
    public void setDataSet(List<DailyWeather> dataSet) {
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdultCustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdultCustomViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.child_recycler, parent, false), parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull AdultCustomViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + dataSet.get(position).forecasts.get(0).dt_txt);
        holder.setDisplay(dataSet.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: "+ dataSet.size());
        return dataSet != null ? dataSet.size() : 0;
    }
}
