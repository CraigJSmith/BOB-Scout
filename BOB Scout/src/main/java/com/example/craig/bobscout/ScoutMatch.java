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

    private TextView dataOutput;
    private Button switchButton;
    private Button scaleButton;
    private Button startButton;

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

        dataOutput = (TextView) findViewById(R.id.dataOutput);
        switchButton = (Button) findViewById(R.id.switchButton);
        scaleButton = (Button) findViewById(R.id.scaleButton);
        startButton = (Button) findViewById(R.id.startButton);

        dataOutput.setMovementMethod(new ScrollingMovementMethod());
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
        startActivity(intent);
        //save to file
    }

    public void enableScoutingButtons(boolean enabled) {
        switchButton.setEnabled(enabled);
        scaleButton.setEnabled(enabled);
    }

    public void goSwitch(View v) {
        long time = System.currentTimeMillis() - startTime;
        Action a = new Action("Switch", time);
        actions.add(a);
        outputData();
    }

    public void goScale(View v) {
        long time = System.currentTimeMillis() - startTime;
        Action a = new Action("Scale", time);
        actions.add(a);
        outputData();
    }

    public void outputData() {
        data = matchNum + "," + teamNum + "\n";
        for (Action a : actions) {
            data += a.getType() + ", " + a.getTime() + "\n";
        }
        dataOutput.setText(data);
    }
}
