package com.example.craig.bobscout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SubmitMatch extends AppCompatActivity {

    private TextView dataOutput;
    private boolean autoCross;
    private boolean autoCubeSwitch;
    private boolean autoCubeScale;
    private boolean autoCubePickup;
    private boolean discard;
    private boolean unusual;
    private boolean tipped;
    private boolean damDrive;
    private boolean damIntake;
    private boolean damLift;
    private boolean def;
    private boolean push;
    private boolean selfclimb;
    private boolean otherclimb;

    private String matchNum;
    private String teamNum;
    private String output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        dataOutput = findViewById(R.id.dataOutput);
        dataOutput.setMovementMethod(new ScrollingMovementMethod());

        Bundle extras = getIntent().getExtras();

        // Match Setup
        matchNum = extras.getString("MATCH");
        teamNum = extras.getString("TEAM");

        // Teleop
        String teleop = extras.getString("DATA");

        // Auto
        String autoStart = extras.getString("AUTO_START");
        autoCross = extras.getBoolean("AUTO_CROSS");
        autoCubeSwitch = extras.getBoolean("AUTO_SWITCH");
        autoCubeScale = extras.getBoolean("AUTO_SCALE");
        autoCubePickup = extras.getBoolean("AUTO_PICKUP");
        String auto = autoStart + "," + boolToInt(autoCross) + "," + boolToInt(autoCubeSwitch) + "," + boolToInt(autoCubeScale) + "," + boolToInt(autoCubePickup) + ",";

        // End
        discard = extras.getBoolean("DISCARD");
        unusual = extras.getBoolean("UNUSUAL");
        tipped = extras.getBoolean("TIPPED");
        damDrive = extras.getBoolean("DAMDRIVE");
        damIntake = extras.getBoolean("DAMINTAKE");
        damLift = extras.getBoolean("DAMLIFT");
        def = extras.getBoolean("DEF");
        push = extras.getBoolean("PUSH");
        selfclimb = extras.getBoolean("SELFCLIMB");
        otherclimb = extras.getBoolean("OTHERCLIMB");
        String end = boolToInt(discard) + "," + boolToInt(unusual) + "," + boolToInt(tipped) + "," + boolToInt(damDrive) + "," + boolToInt(damIntake) + "," +
                     boolToInt(damLift) + "," + boolToInt(def) + "," + boolToInt(push) + "," + boolToInt(selfclimb) + "," + boolToInt(otherclimb);

        output = teleop + matchNum + "," + teamNum + "," + "," + "," + auto + end;

        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File dir = new File(Environment.getExternalStorageDirectory(), "/BOBScout/Matches/");
            dir.mkdirs();
            File file = new File(dir, matchNum + "_" + teamNum + ".csv");

            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file, false);
                fileOutputStream.write(output.getBytes());
                fileOutputStream.close();
                Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_LONG).show();
                Log.d("BOBScout", e.getMessage());
            }

        }
        //match, team, autoCross, autoSwitch, autoScale, autoPickup, action, time, discard, unusual, tipped, damDrive, damIntake, damLift, climb, def
        dataOutput.setText(output);
    }

    public void back(View v) {
        Intent newMatch = new Intent(this, ScoutMatchSetup.class);
        startActivity(newMatch);
    }

    @Override
    public void onBackPressed() {
        back(null);
    }

    private int boolToInt(boolean b) {
        if(b == true) {
            return 1;
        } else {
            return 0;
        }
    }
}
