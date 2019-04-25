package com.example.lab3_2.Activity;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lab3_2.DB.DBHelper;
import com.example.lab3_2.R;

public class MainActivity extends AppCompatActivity {

    private boolean created = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("onCreate", "MainActivity\n-------------------------------------------------------\n");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button showButton = findViewById(R.id.describeButton), showButtonOld = findViewById(R.id.describeButtonOld), changeVersionOfDB = findViewById(R.id.changeDBVersion);
        deleteDatabase("StudentsDB");
        final DBHelper dbHelper = new DBHelper(getApplicationContext());
        dbHelper.getVersionOfDB(1);
        if (savedInstanceState != null)
            created = savedInstanceState.getBoolean("created");
        if (!created) {
            if (dbHelper.newVersion == 2)
                dbHelper.addFiveStudents();
            else
                dbHelper.addFiveStudentsOld();
            created = true;
        }
        showButtonOld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DBHelper.newVersion == 1)
                    startShowActivityOld();
                else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Необходимо сменить версию БД на 1, чтобы использовать эту кнопку", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DBHelper.newVersion == 2)
                    startShowActivity();
                else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Необходимо сменить версию БД на 2, чтобы использовать эту кнопку", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbhelper = new DBHelper(getApplicationContext());
                if (DBHelper.newVersion == 2)
                    dbhelper.addStudent();
                else
                    dbhelper.addStudentOld();
            }
        });
        Button changeButton = findViewById(R.id.changeLastButton);
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbhelper = new DBHelper(getApplicationContext());
                if (DBHelper.newVersion == 2)
                    dbhelper.changeStudent();
                else
                    dbhelper.changeStudentOld();
            }
        });
        changeVersionOfDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dbHelper.getNewVersion() == 2)
                    dbHelper.setVersionOfDB(1);
                else
                    dbHelper.setVersionOfDB(2);
                DBHelper dbhelper = new DBHelper(getApplicationContext(), dbHelper.getNewVersion());
                Toast toast = Toast.makeText(getApplicationContext(), "Была установлена БД версии " + Integer.toString(DBHelper.newVersion), Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.i("onRestoreInstanceState", "MainActivity");
        super.onRestoreInstanceState(savedInstanceState);
        created = savedInstanceState.getBoolean("created");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.i("onSaveInstanceState", "MainActivity");
        super.onSaveInstanceState(outState);
        outState.putBoolean("created", created);
    }

    public void startShowActivityOld(){
        Intent intent = new Intent(MainActivity.this, ShowStudentActivityOld.class);
        startActivity(intent);
    }

    public void startShowActivity(){
        Intent intent = new Intent(MainActivity.this, ShowStudentActivity.class);
        startActivity(intent);
    }
}
