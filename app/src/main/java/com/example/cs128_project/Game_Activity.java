package com.example.cs128_project;

import android.content.Intent;
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
    public TextView time_txt1;
    public TextView time_txt2;
    Button shootbutton;
    Button shootbutton2;
    public Stopwatch stoptime = new Stopwatch(0);
    public Stopwatch stoptime2 = new Stopwatch(0);

    long startTime = 0;
    private RelativeLayout screenOne = null; //screen for player one
    private RelativeLayout screenTwo = null; //screen for player two
    public int milliSec;
    public int sec;
    private ImageView user1ImageView;
    private ImageView user2ImageView;
    private ImageView user1gun;
    private ImageView user2gun;
    private ImageView user1dead;
    private ImageView user2dead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_);

        gameStart();

    }

    public void gameStart(){
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
                    //maybe shouldve used multithreading for time
                    TextView time_txt1 = (TextView) findViewById(R.id.timetxt1);
                    stoptime.stop();
                    sec = stoptime.getSec();
                    milliSec = stoptime.getMilliSec();
                    time_txt1.setText(String.format("%01d", sec) + ":" + String.format("%02d", milliSec));

                    screenTwo.setOnTouchListener(null); //disable ontouch event of player two
                    //show result
                    moveToResults();
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
                    stoptime.stop();
                    sec = stoptime.getSec();
                    milliSec = stoptime.getMilliSec();
                    time_txt2.setText(String.format("%01d", sec) + ":" + String.format("%02d", milliSec));

                    screenOne.setOnTouchListener(null); //disable ontouch event of player one
                    //show result
                    moveToResults();
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

                user1gun = findViewById(R.id.PlayerGun1);
                user1gun.setImageResource(globalVariable.getUser1gun());

                user2gun = findViewById(R.id.PlayerGun2);
                user2gun.setImageResource(globalVariable.getUser2gun());

                user1gun.setVisibility(View.VISIBLE);
                user2gun.setVisibility(View.VISIBLE);

                //shootbutton.setVisibility(View.VISIBLE);
                //shootbutton2.setVisibility(View.VISIBLE);

//                startTime = SystemClock.uptimeMillis();

                stoptime.start();
                stoptime2.start();
            }
        }, 6000);
        //

    }
    public void moveToResults(){
        Intent intent = new Intent(getApplicationContext(),game_result.class);
        Bundle extras = new Bundle();
        extras.putInt("MILLISEC",stoptime.getMilliSec());
        extras.putInt("SECONDS",stoptime.getSec());
        intent.putExtras(extras);
        stoptime.reset();
        startActivity(intent);
    }
}
