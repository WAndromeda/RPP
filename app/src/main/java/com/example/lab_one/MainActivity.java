package com.example.lab_one;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mainRecyclerView;
    private LinkedList<Integer> numbers;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private LinkedList<Integer> image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setTitle("Lab1");
        image = new LinkedList<>();
        numbers = new LinkedList<>();
         for (int i = 1; i <= 1000000; i++) {
             image.add(R.drawable.icon);
             numbers.add(i);
         }

        mainRecyclerView = (RecyclerView) findViewById(R.id.mainRecyclerView);
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(image, numbers);
        mainRecyclerView.setAdapter(myRecyclerViewAdapter);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onDestroy() {
        image.clear();
        numbers.clear();
        myRecyclerViewAdapter = null;
        mainRecyclerView = null;
        super.onDestroy();
    }


}
