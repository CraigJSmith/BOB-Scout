package com.example.craig.bobscout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
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

    private Button start;
    private Button fpickup;
    private Button fdrop;

    private Button p_r1;
    private Button p_r2;
    private Button p_b1;
    private Button p_b2;

    private Button cp_r;
    private Button cp_b;

    private Button sw_r1;
    private Button sw_r2;
    private Button sw_b1;
    private Button sw_b2;

    private Button c_1;
    private Button c_2;
    private Button c_3;
    private Button c_4;
    private Button c_5;
    private Button c_6;
    private Button c_7;
    private Button c_8;
    private Button c_9;
    private Button c_10;
    private Button c_11;
    private Button c_12;

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

        p_r1 = findViewById(R.id.p_r1);
        p_r2 = findViewById(R.id.p_r2);
        p_b1 = findViewById(R.id.p_b1);
        p_b2 = findViewById(R.id.p_b2);

        //cp_r = findViewById(R.id.cp_r);

        sw_r1 = findViewById(R.id.sw_r1);
        sw_r2 = findViewById(R.id.sw_r2);
        sw_b1 = findViewById(R.id.sw_b1);
        sw_b2 = findViewById(R.id.sw_b2);

        c_1 = findViewById(R.id.c_1);
        c_2 = findViewById(R.id.c_2);
        c_3 = findViewById(R.id.c_3);
        c_4 = findViewById(R.id.c_4);
        c_5 = findViewById(R.id.c_5);
        c_6 = findViewById(R.id.c_6);
        c_7 = findViewById(R.id.c_7);
        c_8 = findViewById(R.id.c_8);
        c_9 = findViewById(R.id.c_9);
        c_10 = findViewById(R.id.c_10);
        c_11 = findViewById(R.id.c_11);
        c_12 = findViewById(R.id.c_12);

        sc_1 = findViewById(R.id.sc_1);
        sc_2 = findViewById(R.id.sc_2);

        start = findViewById(R.id.start);
        fpickup = findViewById(R.id.fpickup);
        fdrop = findViewById(R.id.fdrop);
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
        start.setText("Stop");
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
        p_r1.setEnabled(enabled);
        p_r2.setEnabled(enabled);
        p_b1.setEnabled(enabled);
        p_b1.setEnabled(enabled);

        sw_r1.setEnabled(enabled);
        sw_r2.setEnabled(enabled);
        sw_b1.setEnabled(enabled);
        sw_b2.setEnabled(enabled);

        c_1.setEnabled(enabled);
        c_2.setEnabled(enabled);
        c_3.setEnabled(enabled);
        c_4.setEnabled(enabled);
        c_5.setEnabled(enabled);
        c_6.setEnabled(enabled);
        c_7.setEnabled(enabled);
        c_8.setEnabled(enabled);
        c_9.setEnabled(enabled);
        c_10.setEnabled(enabled);
        c_11.setEnabled(enabled);
        c_12.setEnabled(enabled);

        sc_1.setEnabled(enabled);
        sc_2.setEnabled(enabled);

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
