package com.example.craig.bobscout;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
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
    private boolean messedUp;
    private boolean unusualMatch;
    private boolean failedClimb;
    private boolean climbedOthers;
    private boolean droppedOthers;
    private boolean damagedDrivetrain;
    private boolean damagedIntake;
    private boolean playedDefense;

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

        // Auto
        autoCross = extras.getBoolean("AUTO_CROSS");
        autoCubeSwitch = extras.getBoolean("AUTO_SWITCH");
        autoCubeScale = extras.getBoolean("AUTO_SCALE");
        autoCubePickup = extras.getBoolean("AUTO_PICKUP");

        // Teleop
        String teleop = extras.getString("DATA");
        String climb = extras.getString("CLIMBDATA");

        // End
        messedUp = extras.getBoolean("MESSED_UP");
        unusualMatch = extras.getBoolean("UNUSUAL_MATCH");
        failedClimb = extras.getBoolean("FAILED_CLIMB");
        climbedOthers = extras.getBoolean("CLIMBED_OTHERS");
        droppedOthers = extras.getBoolean("DROPPED_OTHERS");
        damagedDrivetrain = extras.getBoolean("DAMAGED_DRIVETRAIN");
        damagedIntake = extras.getBoolean("DAMAGED_INTAKE");
        playedDefense = extras.getBoolean("PLAYED_DEFENSE");

        String discard = String.valueOf(messedUp) + "\n";

        String setup = matchNum + "@" + teamNum + "\n";

        String auto = String.valueOf(autoCross) + "," +
                      String.valueOf(autoCubeSwitch) + "," +
                      String.valueOf(autoCubeScale) + "," +
                      String.valueOf(autoCubePickup) + "\n";

        String end = extras.getString("END");

        output = teleop + end;
       //output = discard + setup + auto + end + teleop + climb;

//        if(!climb.equals(null) && !climb.equals("")) {
//            output += climb;
//        }

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

    @Override
    public void onBackPressed() {
        Intent newMatch = new Intent(this, ScoutMatchSetup.class);
        startActivity(newMatch);
    }
}
