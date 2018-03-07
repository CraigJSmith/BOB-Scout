package com.example.craig.bobscout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ScoutMatchEnd extends AppCompatActivity {

    private ToggleButton messedUp;
    private ToggleButton unusualMatch;
    private ToggleButton failedClimb;
    private ToggleButton climbedOthers;
    private ToggleButton droppedOthers;
    private ToggleButton damagedDrivetrain;
    private ToggleButton damagedIntake;
    private ToggleButton playedDefense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scout_match_end);

        messedUp = findViewById(R.id.messedUp);
        unusualMatch = findViewById(R.id.unusualMatch);
        failedClimb = findViewById(R.id.failedClimb);
        climbedOthers = findViewById(R.id.climbedOthers);
        droppedOthers = findViewById(R.id.droppedOthers);
        damagedDrivetrain = findViewById(R.id.damagedDrivetrain);
        damagedIntake = findViewById(R.id.damagedIntake);
        playedDefense = findViewById(R.id.playedDefense);
    }

    public void submit(View v) {
        Intent intent = new Intent(this, SubmitMatch.class);
        Bundle extras = getIntent().getExtras();

        boolean discard = messedUp.isChecked();
        boolean unusual = unusualMatch.isChecked();
        boolean climbFailed = failedClimb.isChecked();
        boolean climbOthers = climbedOthers.isChecked();
        boolean climbDrop = droppedOthers.isChecked();
        boolean damDrive = damagedDrivetrain.isChecked();
        boolean damIntake = damagedIntake.isChecked();
        boolean def = playedDefense.isChecked();

        String data = extras.getString("DATA");
        String match = extras.getString("MATCH");
        String team = extras.getString("TEAM");
        boolean aCross = extras.getBoolean("AUTO_CROSS");
        boolean aSwitch = extras.getBoolean("AUTO_SWITCH");
        boolean aScale = extras.getBoolean("AUTO_SCALE");
        boolean aPickup = extras.getBoolean("AUTO_PICKUP");

        data += match + "," + team + "," + aCross + "," + aSwitch + "," + aScale + "," + aPickup;
        data += null + ":" + null;
        data += discard + "," + unusual + "," + climbFailed + "," + climbOthers + "," + climbDrop + "," + damDrive + "," + damIntake + "," + def;
        data += "\n";

        extras.putString("END", data);

        intent.putExtras(extras);
        startActivity(intent);
    }


}
