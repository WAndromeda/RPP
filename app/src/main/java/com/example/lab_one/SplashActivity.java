package com.example.lab_one;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        try {
            Thread.sleep(2000);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        finish();
    }
}
