package com.example.lab2.Adapters.RecycleAdapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab2.Activity.MainActivity;
import com.example.lab2.R;
import com.example.lab2.Technology;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter {

    private ArrayList<Technology> technologies;

    public RecyclerViewAdapter(ArrayList<Technology> technologies) {
        super();
        this.technologies = technologies;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        MyViewHolder holder = (MyViewHolder) viewHolder;
        Picasso.get()
                .load("https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/" + technologies.get(position).getGraphic())
                .resize(65, 65)
                .placeholder(R.drawable.civ2_logo)
                .error(R.drawable.notfound)
                .centerCrop()
                .into(holder.imageOfTechnology)
        ;
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ( (MainActivity) v.getContext() ).setViewPagerFragment(position);
            }
        });
        holder.nameOfTechnology.setText(technologies.get(position).getName());
        holder.layout.setBackgroundColor(Color.WHITE);
    }


    @Override
    public int getItemCount() {
        return technologies.size();
    }
}





