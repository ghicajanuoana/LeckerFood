package com.example.FoodApp.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.FoodApp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class BaseActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseDatabase database;
    public String TAG="Oana";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
    }
}