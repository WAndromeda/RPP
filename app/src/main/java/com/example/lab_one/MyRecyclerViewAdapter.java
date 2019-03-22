package com.example.lab_one;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.LinkedList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter {

    private LinkedList<Integer> mList;
    private LinkedList<Integer> imageList;

    MyRecyclerViewAdapter(LinkedList<Integer> imageList, LinkedList<Integer> list){
        super();
        mList = list;
        System.out.println(mList.size());
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        LinearLayout layout;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_layout, parent, false);




        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        MyViewHolder holder = (MyViewHolder) viewHolder;
        holder.mText.setText(TranslateNumsToString.translate(mList.get(position)));
        holder.mImage.setImageResource(imageList.get(position));
        if ( (position + 1) % 2 == 1) holder.layout.setBackgroundColor(Color.WHITE);
        else  holder.layout.setBackgroundColor(Color.LTGRAY);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImage;
        private TextView mText;
        private LinearLayout layout;

        MyViewHolder(View itemView){
            super(itemView);
            mText = itemView.findViewById(R.id.textView);
            mImage = itemView.findViewById(R.id.imageView);
            layout = (LinearLayout) itemView.findViewById(R.id.linearLayoutList);
        }

    }
}
