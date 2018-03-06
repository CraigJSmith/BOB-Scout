package com.example.craig.bobscout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class ScoutMatchClimb extends AppCompatActivity {

    String climbData;
    long startTime;
    ArrayList<Action> climbActions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scout_match_climb);

        Bundle extras = getIntent().getExtras();
        startTime = extras.getLong("STARTTIME");
        climbActions = new ArrayList<Action>();
        climbData = "";

    }
    public void addAction(View v) {
        long time = System.currentTimeMillis() - startTime;
        String action = v.getTag().toString();
        Action a = new Action(action, time);
        climbActions.add(a);
    }

    public void saveData() {
        for (Action a : climbActions) {
            climbData += a.getType() + ":" + a.getTime() +"\n";
        }
    }

    public void submit(View v) {
        saveData();
        Intent intent = new Intent(this, ScoutMatchEnd.class);
        Bundle extras = getIntent().getExtras();

        extras.putString("CLIMBDATA", climbData);

        intent.putExtras(extras);
        startActivity(intent);
    }
}
