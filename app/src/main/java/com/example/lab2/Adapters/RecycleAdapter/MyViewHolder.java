package com.example.lab2.Adapters.RecycleAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lab2.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageOfTechnology;
    public TextView nameOfTechnology;
    public LinearLayout layout;

    MyViewHolder(View itemView) {
        super(itemView);
        nameOfTechnology = itemView.findViewById(R.id.textView);
        imageOfTechnology = itemView.findViewById(R.id.imageView);
        layout = itemView.findViewById(R.id.linearLayoutList);
    }
}