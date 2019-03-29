package com.example.lab2.Fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab2.R;
import com.example.lab2.Technology;
import com.squareup.picasso.Picasso;

public class TechViewPagerFragment extends Fragment {

    public TechViewPagerFragment(){
        super();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            return inflater.inflate(R.layout.viewpager_item_layout, container, false);
        else
            return inflater.inflate(R.layout.viewpager_item_layout_land, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        try{
                Display display = getActivity().getWindowManager().getDefaultDisplay();
                DisplayMetrics outMetrics = new DisplayMetrics();
                display.getMetrics(outMetrics);

                float density = getResources().getDisplayMetrics().density;
                int height = (int) (outMetrics.heightPixels / density) / 2;
                int width = (int) (outMetrics.widthPixels / density) - 10;
                super.onViewCreated(view, savedInstanceState);
                TextView nameOfTech = view.findViewById(R.id.textViewNameOfTechVP), descOfTech = view.findViewById(R.id.textViewDescOfTechVP);
            assert getArguments() != null;
            Technology tech = (Technology) getArguments().get("techItem");
                String helpText = tech.getHelptext(),
                        nameOfTechStr = tech.getName();
                if (helpText != null && !helpText.isEmpty())
                    descOfTech.setText(helpText);
                if (nameOfTechStr != null && !nameOfTechStr.isEmpty())
                    nameOfTech.setText(nameOfTechStr);
                ImageView imageViewTechVP = view.findViewById(R.id.imageViewTechVP);
                imageViewTechVP.setMinimumWidth(width);
                imageViewTechVP.setMinimumHeight(height);
                Picasso.get()
                        .load("https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/" + tech.getGraphic())
                        .resize(100, 100)
                        .placeholder(R.drawable.notfound)
                        .error(R.drawable.notfound)
                        .centerCrop()
                        .into(imageViewTechVP)
                ;
        }catch (NullPointerException ex){
            Log.e("Ошибка Null Techs", ex.getMessage());
        }
    }
}