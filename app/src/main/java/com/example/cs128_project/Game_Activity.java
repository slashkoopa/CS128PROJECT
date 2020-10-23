package com.example.cs128_project;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tomer.fadingtextview.FadingTextView;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Game_Activity extends AppCompatActivity {
    Animation fade_out_anim;
    TextView time_txt1;
    Button shootbutton;
    public Stopwatch stopwatch ;
    long startTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_);
        final FadingTextView ftxt=(FadingTextView)findViewById(R.id.fade_text);
        final ImageView bangimg =(ImageView)findViewById(R.id.bang);

        final Button shootbutton=(Button)findViewById(R.id.shootbtn);

        bangimg.setVisibility(View.INVISIBLE);
        shootbutton.setVisibility(View.INVISIBLE);
        fade_out_anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
        ftxt.startAnimation(fade_out_anim);
        //the function run() will activate after 6000 millis or 6 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ftxt.clearAnimation();
                ftxt.stop();
                ftxt.setVisibility(View.INVISIBLE);
                Random rand = new Random();
                int randomNum = rand.nextInt((10 - 5) + 1) + 5;
                try {
                    TimeUnit.SECONDS.sleep(randomNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                bangimg.setVisibility(View.VISIBLE);
                shootbutton.setVisibility(View.VISIBLE);
                stopwatch.start();
            }
        }, 6000);
        //
    }

    public void gameStart(){

    }

    public void shootClick(View view) {
        TextView time_txt1=(TextView)findViewById(R.id.timetxt1);
        //plan on moving this to stopwatch class
        Long tbuff = 0L;
        long millis = stopwatch.getElapsedTime();
        double seconds = (double) (millis / 1000);
        Long tUpdate = tbuff + millis;
        int sec = (int) (tUpdate/1000);
        sec = sec%60;
        int milliSec =(int) (tUpdate%1000);
        time_txt1.setText(String.format("%01d",sec)+":"+String.format("%02d",milliSec));
        System.out.println(milliSec);
        stopwatch.reset();
        //
    }
}
