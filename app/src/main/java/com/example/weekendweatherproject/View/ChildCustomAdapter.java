package com.example.weekendweatherproject.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.weekendweatherproject.Model.DailyWeather;
import com.example.weekendweatherproject.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;


public class ChildCustomAdapter extends RecyclerView.Adapter<ChildCustomViewHolder> {
    private DailyWeather dataSet;
    ChildCustomViewHolder cardView;
    private static final String TAG = "ChildCustomAdapter";
    DecimalFormat numberFormat = new DecimalFormat("###.#");

    public ChildCustomAdapter(Context context) {
        this.context = context;
    }

    Context context;


    public void setDataSet(DailyWeather dataSet) {
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChildCustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChildCustomViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChildCustomViewHolder holder, int position) {

        double farenheitTemp = (9.0 / 5) * (Double.valueOf(dataSet.forecasts.get(position).main.temp) - 273) + 32;
        holder.weekTemp.setText(Double.toString(Double.parseDouble(numberFormat.format(farenheitTemp))));

        String[] splitDateTime = dataSet.forecasts.get(position).dt_txt.split(" ");
        holder.weekTime.setText(splitDateTime[1]);

        holder.weatherImg.setBackground(setImage(dataSet.forecasts.get(position).weather.get(0).icon));

//        Picasso.get()
//                .load("https://openweathermap.org/img/w/" +dataSet.forecasts.get(position).weather.get(0).icon  +".png")
//                .error(R.drawable.ic_launcher_background)
//                .into(holder.weatherImg);

    }

    public Drawable setImage(String icon) {
        switch (icon.substring(2)) {
            case ("01"):
                return context.getDrawable(R.drawable.ic_01d);
            case ("02"):
                return context.getDrawable(R.drawable.ic_02d);
            case ("03"):
                return context.getDrawable(R.drawable.ic_03d);
            case ("04"):
                return context.getDrawable(R.drawable.ic_04d);
            case ("05"):
                return context.getDrawable(R.drawable.ic_09d);
            case ("06"):
                return context.getDrawable(R.drawable.ic_10d);
            case ("07"):
                return context.getDrawable(R.drawable.ic_11d);
            case ("08"):
                return context.getDrawable(R.drawable.ic_13d);
            case ("09"):
                return context.getDrawable(R.drawable.ic_50d);
            default:
                return context.getDrawable((R.drawable.ic_launcher_background));
        }

//        Log.d(TAG, "setImage: " + icon);
//        if (icon.contains("01"))
//            return context.getDrawable(R.drawable.ic_01d);
//        else if (icon.contains("02"))
//            return context.getDrawable(R.drawable.ic_01d);
//        else if (icon.contains("03"))
//            return context.getDrawable(R.drawable.ic_02d);
//        else if (icon.contains("04"))
//            return context.getDrawable(R.drawable.ic_03d);
//        else if (icon.contains("05"))
//            return context.getDrawable(R.drawable.ic_09d);
//        else if (icon.contains("06"))
//            return context.getDrawable(R.drawable.ic_10d);
//        else if (icon.contains("07"))
//            return context.getDrawable(R.drawable.ic_11d);
//        else if (icon.contains("08"))
//            return context.getDrawable(R.drawable.ic_13d);
//        else if (icon.contains("09"))
//            return context.getDrawable(R.drawable.ic_50d);
//        else
//            return context.getDrawable((R.drawable.ic_launcher_background));
    }

    @Override
    public int getItemCount() {
        return dataSet.forecasts == null ? 0 : dataSet.forecasts.size();
    }
}

