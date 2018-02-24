package com.example.craig.bobscout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class ScoutMatch extends AppCompatActivity {

    private String matchNum;
    private String teamNum;
    private static long startTime;
    private String data;
    private boolean started;
    private ArrayList<Action> actions;
    private ArrayList<View> buttons;
    private View prevButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scout_match);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        matchNum = extras.getString("EXTRA_MATCH");
        teamNum = extras.getString("EXTRA_TEAM");
        startTime = System.currentTimeMillis();
        data = "";
        started = false;
        actions = new ArrayList<Action>();
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

        buttons.add(findViewById(R.id.sc_1));
        buttons.add(findViewById(R.id.fpickup));
        buttons.add(findViewById(R.id.startstop));
        buttons.add(findViewById(R.id.fdrop));
        buttons.add(findViewById(R.id.sc_2));

        buttons.add(findViewById(R.id.c_4));
        buttons.add(findViewById(R.id.c_5));
        buttons.add(findViewById(R.id.c_6));

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
        } else {
            stop(v);
        }
    }

    public void start(View v) {
        enableButtons(true);
        startTime = System.currentTimeMillis();
    }

    public void stop(View v) {
        enableButtons(false);
        saveData();

        Intent intent = new Intent(this, Submit.class);
        Bundle extras = getIntent().getExtras();
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
        if(!v.equals(prevButton) && prevButton != null) {
            ((ToggleButton)prevButton).setChecked(false);
        }
        prevButton = v;

        long time = System.currentTimeMillis() - startTime;
        String action = v.getTag().toString();
        Action a = new Action(action, time);
        actions.add(a);
    }

    public void saveData() {
        for (Action a : actions) {
            data += a.getType() + ":" + a.getTime() +",";
        }
    }
}