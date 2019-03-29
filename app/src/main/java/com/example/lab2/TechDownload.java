package com.example.lab2;

import com.example.lab2.Technology;

import java.util.ArrayList;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface TechDownload {
    @GET("src/data/techs.ruleset.json")
    public Single<ArrayList<Technology > > getTechs();
}