package com.example.craig.bobscout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class ScoutMatch extends AppCompatActivity {

    private ArrayList<Action> actions;
    private static long startTime;
    private String data;
    private String matchNum;
    private String teamNum;
    private boolean started;

    private Button startButton;
    private Button cp_r;
    private Button sw_r1;
    private Button sw_r2;
    private Button c_1;
    private Button c_2;
    private Button c_3;
    private Button c_4;
    private Button c_5;
    private Button c_6;
    private Button sc_1;
    private Button sc_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scout_match);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        matchNum = extras.getString("EXTRA_MATCH");
        teamNum = extras.getString("EXTRA_TEAM");
        started = false;

        startTime = System.currentTimeMillis();
        actions = new ArrayList<Action>();
        data = "";

        cp_r = findViewById(R.id.cp_r);

        sw_r1 = findViewById(R.id.sw_r1);
        sw_r2 = findViewById(R.id.sw_r2);

        c_1 = findViewById(R.id.c_1);
        c_2 = findViewById(R.id.c_2);
        c_3 = findViewById(R.id.c_3);
        c_4 = findViewById(R.id.c_4);
        c_5 = findViewById(R.id.c_5);
        c_6 = findViewById(R.id.c_6);

        sc_1 = findViewById(R.id.sc_1);
        sc_2 = findViewById(R.id.sc_2);

        startButton = findViewById(R.id.startButton);
    }

    public void startAndStopButton(View v) {
        started = !started;

        if(started) {
            start();
        }
        else {
            stop();
        }
    }

    public void start() {
        startTime = System.currentTimeMillis();
        startButton.setText("Stop");
        enableScoutingButtons(true);
    }

    public void stop() {
        Intent intent = new Intent(this, Submit.class);

        Bundle extras = new Bundle();
        extras.putString("EXTRA_MATCH", matchNum);
        extras.putString("EXTRA_TEAM", teamNum);
        extras.putString("EXTRA_DATA", data);
        intent.putExtras(extras);

        startActivity(intent);
    }

    public void enableScoutingButtons(boolean enabled) {
        //todo
    }

    public void addAction(View v) {
        long time = System.currentTimeMillis() - startTime;
        String action = v.getTag().toString();
        Action a = new Action(action, time);
        actions.add(a);
        outputData();
    }

    public void outputData() {
        data = matchNum + "," + teamNum + "\n";
        for (Action a : actions) {
            data += a.getType() + ", " + a.getTime() +"\n";
        }
    }
}
