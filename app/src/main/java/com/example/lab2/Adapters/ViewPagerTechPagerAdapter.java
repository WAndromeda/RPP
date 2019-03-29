package com.example.lab2.Adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.lab2.Fragments.TechViewPagerFragment;
import com.example.lab2.Technology;

import java.util.ArrayList;

 /*
  *     FragmentStatePager - чтобы когда страничка была невидима, она очищалась из памяти
  *     (чтобы метод hide() производил очистку фрагмента из памяти)
  */

public class ViewPagerTechPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Technology> technologies;

    public ViewPagerTechPagerAdapter(FragmentManager fM, ArrayList<Technology> technologies) {
        super(fM);
        this.technologies = technologies;
    }

    @Override
    public Fragment getItem(int position) {
        TechViewPagerFragment techViewPagerFragment = new TechViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("techItem", technologies.get(position));
        techViewPagerFragment.setArguments(bundle);
        return techViewPagerFragment;
    }

    @Override
    public int getCount() {
        return technologies.size();
    }
}