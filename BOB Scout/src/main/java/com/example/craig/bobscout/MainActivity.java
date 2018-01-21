package com.example.craig.bobscout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Action> actions;
    private static long startTime;
    private String data;

    private TextView dataOutput;
    private Button switchButton;
    private Button scaleButton;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startTime = System.currentTimeMillis();
        actions = new ArrayList<Action>();
        data = "";

        dataOutput = (TextView) findViewById(R.id.dataOutput);
        switchButton = (Button) findViewById(R.id.switchButton);
        scaleButton = (Button) findViewById(R.id.scaleButton);
        startButton = (Button) findViewById(R.id.startButton);
    }

    public void start(View v) {
        startTime = System.currentTimeMillis() / 1000;
        switchButton.setEnabled(true);
        scaleButton.setEnabled(true);
        startButton.setEnabled(false);
    }

    public void goSwitch(View v) {
        long time = System.currentTimeMillis() / 1000 - startTime;
        Action a = new Action("Switch", time);
        actions.add(a);
        outputData();
    }

    public void goScale(View v) {
        long time = System.currentTimeMillis() / 1000 - startTime;
        Action a = new Action("Scale", time);
        actions.add(a);
        outputData();
    }

    public void outputData() {
        data = "";
        for (Action a : actions) {
            data += a.getType() + ", " + a.getTime() + "\n";
        }
        dataOutput.setText(data);
    }
}
