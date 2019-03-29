package com.example.lab2.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.lab2.Fragments.TechListFragment;
import com.example.lab2.Fragments.TechViewPagerFragmentControl;
import com.example.lab2.R;
import com.example.lab2.Technology;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Technology> technologies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Lab2 - Technologies of Civilization");
        technologies = Objects.requireNonNull(getIntent().getExtras()).getParcelableArrayList("techArray");

        FragmentManager fM = getSupportFragmentManager();

        if (fM.findFragmentByTag("recycle_list_fragment") == null){

            TechListFragment techListFragment = new TechListFragment();
            Bundle bundleRecycle = new Bundle();
            bundleRecycle.putParcelableArrayList("techArray", technologies);
            techListFragment.setArguments(bundleRecycle);

            TechViewPagerFragmentControl techViewPagerFragmentControl = new TechViewPagerFragmentControl();
            Bundle bundleViewPager = new Bundle();
            bundleViewPager.putParcelableArrayList("techArray", technologies);
            techViewPagerFragmentControl.setArguments(bundleViewPager);

            fM.beginTransaction()
                    .add(R.id.mainLayout, techViewPagerFragmentControl, "viewpager_fragment")
                    .add(R.id.mainLayout, techListFragment, "recycle_list_fragment")
                    .commit();
            fM.popBackStackImmediate();
            setRecyclerViewFragment();
        }
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void setRecyclerViewFragment(){
        getSupportFragmentManager().beginTransaction()
                .show(Objects.requireNonNull(getSupportFragmentManager().findFragmentByTag("recycle_list_fragment")))
                .hide(Objects.requireNonNull(getSupportFragmentManager().findFragmentByTag("viewpager_fragment")))
                .commit();
    }

    public void setViewPagerFragment(int position){
        try {
            TechViewPagerFragmentControl techViewPagerFragmentControl = (TechViewPagerFragmentControl) getSupportFragmentManager().findFragmentByTag("viewpager_fragment");
            techViewPagerFragmentControl.setPosition(position);
            getSupportFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .show(Objects.requireNonNull(getSupportFragmentManager().findFragmentByTag("viewpager_fragment")))
                    .hide(Objects.requireNonNull(getSupportFragmentManager().findFragmentByTag("recycle_list_fragment")))
                    .commit();
        }catch (Exception ex){
            Log.e("Ошибка вызова ViewPager", ex.getMessage());
        }
    }

}

