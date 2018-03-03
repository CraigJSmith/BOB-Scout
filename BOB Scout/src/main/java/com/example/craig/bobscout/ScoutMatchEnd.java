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

        extras.putBoolean("MESSED_UP", messedUp.isChecked());
        extras.putBoolean("UNUSUAL_MATCH", unusualMatch.isChecked());
        extras.putBoolean("FAILED_CLIMB", failedClimb.isChecked());
        extras.putBoolean("CLIMBED_OTHERS", climbedOthers.isChecked());
        extras.putBoolean("DROPPED_OTHERS", droppedOthers.isChecked());
        extras.putBoolean("DAMAGED_DRIVETRAIN", damagedDrivetrain.isChecked());
        extras.putBoolean("DAMAGED_INTAKE", damagedIntake.isChecked());
        extras.putBoolean("PLAYED_DEFENSE", playedDefense.isChecked());

        intent.putExtras(extras);
        startActivity(intent);
    }


}
