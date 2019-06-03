package com.example.weekendweatherproject.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weekendweatherproject.R;

public class ChildCustomViewHolder extends RecyclerView.ViewHolder {

    AdultCustomAdapter customAdapter;
    public TextView weekTime,weekTemp, dayname;
    public ImageView weatherImg;

    public ChildCustomViewHolder(@NonNull View itemView) {
        super(itemView);

        weekTime = itemView.findViewById(R.id.tv_time);
        weekTemp = itemView.findViewById(R.id.tv_temp);
        weatherImg = itemView.findViewById(R.id.item_layout);
        dayname = itemView.findViewById(R.id.tv_date);
    }
}
