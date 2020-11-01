package com.example.cs128_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class game_result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_result);
        TextView scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        TextView highScoreLabel = (TextView) findViewById(R.id.highScoreLabel);
        //int score = getIntent().getIntExtra("SCORE",0);
        Bundle extras = getIntent().getExtras();
        int millisec = extras.getInt("MILLISEC",0);
        int sec = extras.getInt("SECONDS",0);
        scoreLabel.setText(String.format("%01d", sec) + ":" + String.format("%02d", millisec));

        System.out.println("milli: "+millisec);
        System.out.println("sec: "+sec);

        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highscore_ms=settings.getInt("HIGH_SCOREms",0);
        SharedPreferences settings2 = getSharedPreferences("GAME_DATA2", Context.MODE_PRIVATE);
        int highscore_sec=settings2.getInt("HIGH_SCOREsec",0);

        DateFormat dateFormat = new SimpleDateFormat("ss.SSS");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND,sec);
        cal.add(Calendar.MILLISECOND,millisec);

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.SECOND,highscore_sec);
        cal2.add(Calendar.MILLISECOND,highscore_ms);
        System.out.println(cal);
        System.out.println(cal2);
        System.out.println("compare cal to cal2 "+cal.compareTo(cal2));
        System.out.println("compare cal2 to cal "+cal2.compareTo(cal));
        if((highscore_sec == 0) && (highscore_ms==0)){
            System.out.println("date true");
            highScoreLabel.setText(String.format("%01d", sec) + "." + String.format("%02d", millisec));

            //save
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCOREms",millisec);
            editor.commit();
            SharedPreferences.Editor editor2 = settings2.edit();
            editor2.putInt("HIGH_SCOREsec",sec);
            editor2.commit();
        }
        else{
            if((cal.compareTo(cal2)==-1)){
                System.out.println("date true");
                highScoreLabel.setText(String.format("%01d", sec) + "." + String.format("%02d", millisec));

                //save
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("HIGH_SCOREms",millisec);
                editor.commit();
                SharedPreferences.Editor editor2 = settings2.edit();
                editor2.putInt("HIGH_SCOREsec",sec);
                editor2.commit();
            }
            else{
                System.out.println("date false");
                highScoreLabel.setText(String.format("%01d", highscore_sec) + "." + String.format("%02d", highscore_ms));
            }
        }
    }

    public void tryAgain(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}