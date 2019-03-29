package com.example.lab2.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab2.Adapters.RecycleAdapter.RecyclerViewAdapter;
import com.example.lab2.R;
import com.example.lab2.Technology;

import java.util.ArrayList;

public class TechListFragment extends Fragment {

    public TechListFragment(){
        super();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recycleview_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assert getArguments() != null;
        ArrayList<Technology> technologies = getArguments().getParcelableArrayList("techArray");
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(technologies);
        RecyclerView mainRecyclerView = view.findViewById(R.id.mainRecyclerView);

        mainRecyclerView.setAdapter(recyclerViewAdapter);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
