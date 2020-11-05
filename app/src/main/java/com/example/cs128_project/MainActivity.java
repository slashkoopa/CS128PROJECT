package com.example.cs128_project;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private static final int[] idArray = {R.id.red, R.id.green, R.id.blue, R.id.brown, R.id.red2, R.id.green2, R.id.blue2, R.id.brown2};

    private Button[] roundButton = new Button[idArray.length];

    MediaPlayer gmtheme;
    // Calling Application class (see application tag in AndroidManifest.xml)

    int i;
    public String currentRound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //plays sound when running the app
        gmtheme = MediaPlayer.create(MainActivity.this,R.raw.soundtheme);

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GuidelineActivity.class);
                startActivity(intent);
            }
        });

        Spinner rounds = findViewById(R.id.rounds);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.rounds, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rounds.setAdapter(adapter);
        rounds.setOnItemSelectedListener(this);

        for(i = 0; i<idArray.length; i++){
            roundButton[i] = (Button)findViewById(idArray[i]);

            roundButton[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer color = v.getId();

                    switch(color){
                        case R.id.red:
                            globalVariable.setUser1color(R.drawable.amongusred);
                            globalVariable.setUser1gun(R.drawable.redgun);
                            globalVariable.setUser1dead(R.drawable.deadred);
                            Toast.makeText(getApplicationContext(), "Player 1 changed color to red", Toast.LENGTH_SHORT).show();
                            break;

                        case R.id.green:
                            globalVariable.setUser1color(R.drawable.amongusgreen);
                            globalVariable.setUser1gun(R.drawable.greengun);
                            globalVariable.setUser1dead(R.drawable.deadgreen);
                            Toast.makeText(getApplicationContext(), "Player 1 changed color to green", Toast.LENGTH_SHORT).show();
                            break;

                        case R.id.blue:
                            globalVariable.setUser1color(R.drawable.amongusblue);
                            globalVariable.setUser1gun(R.drawable.bluegun);
                            globalVariable.setUser1dead(R.drawable.deadblue);
                            Toast.makeText(getApplicationContext(), "Player 1 changed color to blue", Toast.LENGTH_SHORT).show();
                            break;

                        case R.id.brown:
                            globalVariable.setUser1color(R.drawable.amongusbrown);
                            globalVariable.setUser1gun(R.drawable.browngun);
                            globalVariable.setUser1dead(R.drawable.deadbrown);
                            Toast.makeText(getApplicationContext(), "Player 1 changed color to brown", Toast.LENGTH_SHORT).show();
                            break;

                        case R.id.red2:
                            globalVariable.setUser2color(R.drawable.amongusred);
                            globalVariable.setUser2gun(R.drawable.redgun);
                            globalVariable.setUser2dead(R.drawable.deadred);
                            Toast.makeText(getApplicationContext(), "Player 2 changed color to red", Toast.LENGTH_SHORT).show();
                            break;

                        case R.id.green2:
                            globalVariable.setUser2color(R.drawable.amongusgreen);
                            globalVariable.setUser2gun(R.drawable.greengun);
                            globalVariable.setUser2dead(R.drawable.deadgreen);
                            Toast.makeText(getApplicationContext(), "Player 2 changed color to green", Toast.LENGTH_SHORT).show();
                            break;

                        case R.id.blue2:
                            globalVariable.setUser2color(R.drawable.amongusblue);
                            globalVariable.setUser2gun(R.drawable.bluegun);
                            globalVariable.setUser2dead(R.drawable.deadblue);
                            Toast.makeText(getApplicationContext(), "Player 2 changed color to blue", Toast.LENGTH_SHORT).show();
                            break;

                        case R.id.brown2:
                            globalVariable.setUser2color(R.drawable.amongusbrown);
                            globalVariable.setUser2gun(R.drawable.browngun);
                            globalVariable.setUser2dead(R.drawable.deadbrown);
                            Toast.makeText(getApplicationContext(), "Player 2 changed color to brown", Toast.LENGTH_SHORT).show();
                            break;
                    }

                }
            });

        }

    }

    public void startGame(View view) {
        Intent intent = new Intent(getBaseContext(), Game_Activity.class);
        currentRound = currentRound.substring(6,7);
        intent.putExtra("ROUNDNUM", currentRound);
        System.out.println("sending... "+currentRound);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();

        currentRound = text;
        getCurrentRound(currentRound);
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume(){ //plays music while on standby
        super.onResume();
        gmtheme.start();
        //loops music
        gmtheme.setLooping(true);
    }

    @Override
    protected void onPause(){ //stops music when game is finished
        super.onPause();
        gmtheme.release();
        finish();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public String getCurrentRound(String round){
        return currentRound=round;
    }
}