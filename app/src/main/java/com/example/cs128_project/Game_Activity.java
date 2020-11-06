package com.example.cs128_project;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.icu.util.ICUUncheckedIOException;
import android.media.MediaPlayer;
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
    //placeholder round value should be grabbing from main activity
    public int rounds;
    MediaPlayer countvc;
    MediaPlayer shootsnd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_);
        Intent intent = getIntent();
        String srounds = intent.getStringExtra("ROUNDNUM");
        rounds = Integer.parseInt(srounds);
        gameStart();


    }

    @SuppressLint("ResourceType")
    public void gameStart(){
        final FadingTextView ftxt=(FadingTextView)findViewById(R.id.fade_text);
        final FadingTextView ftxt2=(FadingTextView)findViewById(R.id.fade_text2);
        final ImageView bangimg =(ImageView)findViewById(R.id.imageView2);
        GlobalClass globalVariable = (GlobalClass) getApplicationContext();

        shootsnd = MediaPlayer.create(this, R.raw.bangsnd);
        countvc = MediaPlayer.create(this, R.raw.countbg);
        countvc.start();

        //sets the sound file into layout to play sound once players clicked the screen
        screenOne = findViewById(R.raw.gunshot);
        screenTwo = findViewById(R.raw.gunshot);
        final MediaPlayer gshot = MediaPlayer.create(this, R.raw.gunshot);

        bangimg.setVisibility(View.INVISIBLE);

        user1ImageView = findViewById(R.id.PlayerChar1);
        user1ImageView.setImageResource(globalVariable.getUser1color());
        user2ImageView = findViewById(R.id.PlayerChar2);
        user2ImageView.setImageResource(globalVariable.getUser2color());

        //P1 GUN PIC
        user1gun = findViewById(R.id.PlayerGun1);
        user1gun.setImageResource(globalVariable.getUser1gun());
        user1gun.setVisibility(View.INVISIBLE);

        //P2 GUN PIC
        user2gun = findViewById(R.id.PlayerGun2);
        user2gun.setImageResource(globalVariable.getUser2gun());
        user2gun.setVisibility(View.INVISIBLE);

        //P1 DEAD PIC
        user1dead = findViewById(R.id.playerDead1);
        user1dead.setImageResource(globalVariable.getUser1dead());
        user1dead.setVisibility(View.INVISIBLE);

        //P2 DEAD PIC
        user2dead = findViewById(R.id.playerDead2);
        user2dead.setImageResource(globalVariable.getUser2dead());
        user2dead.setVisibility(View.INVISIBLE);
        screenTwo = (RelativeLayout) findViewById(R.id.screenTwo); //sets touch listener if the player two touched the screen
        screenOne = (RelativeLayout) findViewById(R.id.screenOne); //sets touch listener if the player one touched the screen
        screenOne.setEnabled(false);
        screenTwo.setEnabled(false);

        setRound();

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
                int randomNum = rand.nextInt((10000 - 5000) + 1) + 5000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bangimg.setVisibility(View.VISIBLE);
                        shootsnd.start();
                        stoptime.start();
                        stoptime2.start();
                        screenOne.setEnabled(true);
                        screenTwo.setEnabled(true);
                    }
                }, randomNum);

                screenOne.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if(event.getAction()== MotionEvent.ACTION_DOWN) {

                            //maybe shouldve used multithreading for time
                            gshot.start(); //starts to play sound when player 1 clicks the screen
                            TextView time_txt1 = (TextView) findViewById(R.id.timetxt1);
                            stoptime.stop();
                            sec = stoptime.getSec();
                            milliSec = stoptime.getMilliSec();
                            time_txt1.setText(String.format("%01d", sec) + ":" + String.format("%02d", milliSec));


                            screenTwo.setEnabled(false); //disable ontouch event of player two
                            screenOne.setEnabled(false);
                            user1gun.setVisibility(View.VISIBLE);
                            user2dead.setVisibility(View.VISIBLE);
                            user2ImageView.setVisibility(View.INVISIBLE);
                            //add p2 dead code here

                            //code below will run after 6 seconds
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = getIntent();
                                    int currentround = intent.getIntExtra("rounds",1); // gets current round number
<<<<<<< HEAD
                                    int p1points= intent.getIntExtra("p1points",0);
                                    int p2points= intent.getIntExtra("p2points",0);
                                    p1points++;
=======
>>>>>>> parent of ccc6d39... round display(halfdone)
                                    if(currentround==rounds){
                                        //show result

                                        moveToResults(p1points,p2points);
                                    }
                                    else{
                                        resetGame(currentround,p1points,p2points);
                                    }
                                }
                            }, 6000);
                        }
                        return true;
                    }
                });

                // Calling Application class (see application tag in AndroidManifest.xml)
                screenTwo.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if(event.getAction()== MotionEvent.ACTION_DOWN) {
                            gshot.start(); //starts to play sound when player 2 clicks the screen
                            TextView time_txt2 = (TextView) findViewById(R.id.timetxt2);
                            stoptime.stop();
                            sec = stoptime.getSec();
                            milliSec = stoptime.getMilliSec();
                            time_txt2.setText(String.format("%01d", sec) + ":" + String.format("%02d", milliSec));


                            screenOne.setEnabled(false); //disable ontouch event of player one
                            screenTwo.setEnabled(false);
                            user2gun.setVisibility(View.VISIBLE);
                            user1dead.setVisibility(View.VISIBLE);
                            user1ImageView.setVisibility(View.INVISIBLE);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = getIntent();
                                    int currentround = intent.getIntExtra("rounds",1); // gets current round number
<<<<<<< HEAD
                                    int p2points= intent.getIntExtra("p2points",0);
                                    int p1points = intent.getIntExtra("p1points",0);
                                    p2points++;
=======
>>>>>>> parent of ccc6d39... round display(halfdone)
                                    if(currentround==rounds){
                                        //show result

                                        moveToResults(p1points,p2points);
                                    }
                                    else{
                                        resetGame(currentround,p1points,p2points);
                                    }
                                }
                            }, 6000);

                        }
                        return true;
                    }
                });
            }
        }, 6000);
        //

    }
    public void moveToResults(int p1points, int p2points){
        Intent intent = new Intent(getApplicationContext(),game_result.class);
        Bundle extras = new Bundle();
        extras.putInt("MILLISEC",stoptime.getMilliSec());
        extras.putInt("SECONDS",stoptime.getSec());
        extras.putInt("p1points",p1points);
        extras.putInt("p2points",p2points);
        System.out.println("sending...p1 "+p1points+" p2 "+p2points);
        intent.putExtras(extras);
        stoptime.reset();
        startActivity(intent);
    }
    //reloads activity with new value
    public void resetGame(int currentrounds, int p1points, int p2points){
        currentrounds++;
        Intent intent = getIntent();
        intent.putExtra("rounds",currentrounds);
        intent.putExtra("p1points",p1points);
        intent.putExtra("p2points",p2points);
        finish();
        startActivity(intent);
    }
    public void setRound(){
        Intent intent = getIntent();
        int currentround = intent.getIntExtra("rounds",1);
        final TextView p1roundtxt=(TextView)findViewById(R.id.p1round);
        final TextView p2roundtxt=(TextView)findViewById(R.id.p2round);
        p1roundtxt.setText("ROUND "+currentround);
        p2roundtxt.setText("ROUND "+currentround);
    }
    @Override
    protected void onPause(){ //stops music when game is finished
        super.onPause();
        countvc.release();
        shootsnd.release();
        finish();
    }


}
