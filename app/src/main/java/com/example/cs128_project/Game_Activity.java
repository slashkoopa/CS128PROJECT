package com.example.cs128_project;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tomer.fadingtextview.FadingTextView;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Game_Activity extends AppCompatActivity {
    Animation fade_out_anim;
    TextView time_txt1;
    TextView time_txt2;
    Button shootbutton;
    Button shootbutton2;
    public Stopwatch stoptime ;
    long startTime = 0;
    private RelativeLayout screenOne = null; //screen for player one
    private RelativeLayout screenTwo = null; //screen for player two
    private ImageView user1ImageView;
    private ImageView user2ImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_);
        final FadingTextView ftxt=(FadingTextView)findViewById(R.id.fade_text);
        final FadingTextView ftxt2=(FadingTextView)findViewById(R.id.fade_text2);
        final ImageView bangimg =(ImageView)findViewById(R.id.bang);


        //final Button shootbutton=(Button)findViewById(R.id.shootbtn);
        //final Button shootbutton2=(Button)findViewById(R.id.shootbtn2);

        bangimg.setVisibility(View.INVISIBLE);
        //shootbutton.setVisibility(View.INVISIBLE);
        //shootbutton2.setVisibility(View.INVISIBLE);

        screenOne = (RelativeLayout) findViewById(R.id.screenOne); //sets touch listener if the player one touched the screen
        screenOne.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()== MotionEvent.ACTION_DOWN) {
                    TextView time_txt1 = (TextView) findViewById(R.id.timetxt1);
                    //plan on moving this to stopwatch class
                    Long tbuff = 0L;
                    long millis = SystemClock.uptimeMillis() - startTime;
                    double seconds = (double) (millis / 1000);
                    Long tUpdate = tbuff + millis;
                    int sec = (int) (tUpdate / 1000);
                    sec = sec % 60;
                    int milliSec = (int) (tUpdate % 1000);
                    time_txt1.setText(String.format("%01d", sec) + ":" + String.format("%02d", milliSec));
                    System.out.println(milliSec);
                }
                return true;
            }
        });

        // Calling Application class (see application tag in AndroidManifest.xml)
        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

        user1ImageView = findViewById(R.id.PlayerChar1);
        user1ImageView.setImageResource(globalVariable.getUser1color());

        user2ImageView = findViewById(R.id.PlayerChar2);
        user2ImageView.setImageResource(globalVariable.getUser2color());


        screenTwo = (RelativeLayout) findViewById(R.id.screenTwo); //sets touch listener if the player two touched the screen
        screenTwo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()== MotionEvent.ACTION_DOWN) {
                    TextView time_txt2 = (TextView) findViewById(R.id.timetxt2);
                    //plan on moving this to stopwatch class
                    Long tbuff = 0L;
                    long millis = SystemClock.uptimeMillis() - startTime;
                    double seconds = (double) (millis / 1000);
                    Long tUpdate = tbuff + millis;
                    int sec = (int) (tUpdate / 1000);
                    sec = sec % 60;
                    int milliSec = (int) (tUpdate % 1000);
                    time_txt2.setText(String.format("%01d", sec) + ":" + String.format("%02d", milliSec));
                    System.out.println(milliSec);
                }
                return true;
            }
        });

        fade_out_anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
        ftxt.startAnimation(fade_out_anim);
        ftxt2.startAnimation(fade_out_anim);
        //the function run() will activate after 6000 millis or 6 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ftxt.clearAnimation();
                ftxt.stop();
                ftxt.setVisibility(View.INVISIBLE);

                ftxt2.clearAnimation();
                ftxt2.stop();
                ftxt2.setVisibility(View.INVISIBLE);

                Random rand = new Random();
                int randomNum = rand.nextInt((10 - 5) + 1) + 5;
                try {
                    TimeUnit.SECONDS.sleep(randomNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                bangimg.setVisibility(View.VISIBLE);
                //shootbutton.setVisibility(View.VISIBLE);
                //shootbutton2.setVisibility(View.VISIBLE);

                startTime = SystemClock.uptimeMillis();
            }
        }, 6000);
        //
    }

    public void gameStart(){

    }

}