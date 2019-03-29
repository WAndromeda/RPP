package com.example.lab2.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab2.Adapters.ViewPagerTechPagerAdapter;
import com.example.lab2.R;
import com.example.lab2.Technology;

import java.util.ArrayList;

public class TechViewPagerFragmentControl extends Fragment {

    private ViewPager viewPager;

    public TechViewPagerFragmentControl(){
        super();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.viewpager_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assert getArguments() != null;
        ArrayList<Technology> technologies = getArguments().getParcelableArrayList("techArray");
        ViewPagerTechPagerAdapter recyclerViewAdapter = new ViewPagerTechPagerAdapter(getChildFragmentManager(), technologies);
        viewPager = view.findViewById(R.id.viewPagerTechnology);
        viewPager.setAdapter(recyclerViewAdapter);
        viewPager.setOffscreenPageLimit(3);
    }

    @NonNull
    public void setPosition(int position){
        viewPager.setCurrentItem(position);
    }

}
