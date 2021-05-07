package com.example.retrofitandroomandsharedpreferences.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.retrofitandroomandsharedpreferences.Fragments.LiveFragment;
import com.example.retrofitandroomandsharedpreferences.Fragments.SavedFragment;
import com.example.retrofitandroomandsharedpreferences.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new LiveFragment() ).commit();

    }


    public void liveAction(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new LiveFragment() ).commit();


    }

    public void savedAction(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new SavedFragment() ).commit();



    }
}