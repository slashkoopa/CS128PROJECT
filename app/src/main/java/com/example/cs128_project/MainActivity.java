package com.example.cs128_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void startGame(View view) {
        Intent intent = new Intent(MainActivity.this, Game_Activity.class);
        startActivity(intent);
        //test changes
    }
}