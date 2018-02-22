package com.example.craig.bobscout;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import java.util.ArrayList;

public class ScoutMatch extends AppCompatActivity {

    private ArrayList<Action> actions;
    private static long startTime;
    private String data;
    private String matchNum;
    private String teamNum;
    private boolean started;
    private ArrayList<View> buttons;
    private Button startStopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scout_match);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        matchNum = extras.getString("EXTRA_MATCH");
        teamNum = extras.getString("EXTRA_TEAM");
        started = false;

        startStopButton = findViewById(R.id.startstop);

        startTime = System.currentTimeMillis();
        actions = new ArrayList<Action>();
        data = "";

        buttons = new ArrayList<View>();

        buttons.add(findViewById(R.id.p_r1));
        buttons.add(findViewById(R.id.ex_r));
        buttons.add(findViewById(R.id.p_r2));

        buttons.add(findViewById(R.id.cp_r));

        buttons.add(findViewById(R.id.sw_r1));
        buttons.add(findViewById(R.id.sw_r2));

        buttons.add(findViewById(R.id.c_1));
        buttons.add(findViewById(R.id.c_2));
        buttons.add(findViewById(R.id.c_3));
        buttons.add(findViewById(R.id.c_4));
        buttons.add(findViewById(R.id.c_5));
        buttons.add(findViewById(R.id.c_6));

        buttons.add(findViewById(R.id.sc_1));
        buttons.add(findViewById(R.id.fpickup));
        buttons.add(findViewById(R.id.startstop));
        buttons.add(findViewById(R.id.fdrop));
        buttons.add(findViewById(R.id.sc_2));

        buttons.add(findViewById(R.id.c_7));
        buttons.add(findViewById(R.id.c_8));
        buttons.add(findViewById(R.id.c_9));
        buttons.add(findViewById(R.id.c_10));
        buttons.add(findViewById(R.id.c_11));
        buttons.add(findViewById(R.id.c_12));

        buttons.add(findViewById(R.id.sw_b1));
        buttons.add(findViewById(R.id.sw_b2));

        buttons.add(findViewById(R.id.cp_b));

        buttons.add(findViewById(R.id.p_b1));
        buttons.add(findViewById(R.id.ex_b));
        buttons.add(findViewById(R.id.p_b2));
    }

    public void startAndStopButton(View v) {
        started = !started;

        if(started) {
            start(v);
        }
        else {
            stop(v);
        }
    }

    public void start(View v) {
        enableButtons(true);
        startTime = System.currentTimeMillis();
        startStopButton.setText("Stop");
    }

    public void stop(View v) {
        enableButtons(false);
        saveData();
        Intent intent = new Intent(this, Submit.class);

        Bundle extras = new Bundle();
        extras.putString("EXTRA_MATCH", matchNum);
        extras.putString("EXTRA_TEAM", teamNum);
        extras.putString("EXTRA_DATA", data);
        intent.putExtras(extras);

        startActivity(intent);
    }

    private void enableButtons(boolean enabled) {
        for(View button : buttons){
            if(button instanceof Button)
                button.setEnabled(enabled);
        }
    }

    public void addAction(View v) {
        long time = System.currentTimeMillis() - startTime;
        String action = v.getTag().toString();
        Action a = new Action(action, time);
        actions.add(a);
    }

    public void saveData() {
        data = matchNum + "@" + teamNum + ",";
        for (Action a : actions) {
            data += a.getType() + ":" + a.getTime() +",";
        }
    }
}
