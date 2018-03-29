package com.example.craig.bobscout;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ScoutMatchEnd extends AppCompatActivity {

    private ToggleButton messedUp;
    private ToggleButton unusualMatch;
    private ToggleButton robotTipped;
    private ToggleButton damagedLift;
    private ToggleButton damagedDrivetrain;
    private ToggleButton damagedIntake;
    private ToggleButton playedDefense;
    private ToggleButton pushBot;
    private RadioGroup climbSelf;
    private RadioGroup climbSelfOther;
    private RadioGroup climbOther;
    private RadioButton selfSucc;
    private RadioButton selfFail;
    private RadioButton selfOtherSucc;
    private RadioButton selfOtherFail;
    private RadioButton otherSucc;
    private RadioButton otherFail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scout_match_end);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        messedUp = findViewById(R.id.messedUp);
        unusualMatch = findViewById(R.id.unusualMatch);
        robotTipped = findViewById(R.id.robotTipped);
        damagedDrivetrain = findViewById(R.id.damagedDrivetrain);
        damagedIntake = findViewById(R.id.damagedIntake);
        damagedLift = findViewById(R.id.damagedLift);
        playedDefense = findViewById(R.id.playedDefense);
        pushBot = findViewById(R.id.pushBot);
        climbSelf = findViewById(R.id.climbSelf);
        climbSelfOther = findViewById(R.id.climbSelfOther);
        climbOther = findViewById(R.id.climbOther);
        selfSucc = findViewById(R.id.selfSucc);
        selfFail = findViewById(R.id.selfFail);
        selfOtherSucc = findViewById(R.id.selfOtherSucc);
        selfOtherFail = findViewById(R.id.selfOtherFail);
        otherSucc = findViewById(R.id.otherSucc);
        otherFail = findViewById(R.id.otherFail);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void submit(View v) {
        Intent intent = new Intent(this, SubmitMatch.class);
        Bundle extras = getIntent().getExtras();
        String data = extras.getString("DATA");
        String match = extras.getString("MATCH");
        String team = extras.getString("TEAM");

        boolean selfClimb = false;
        boolean otherClimb = false;
        if(climbSelf.getCheckedRadioButtonId() == selfSucc.getId()) {
            selfClimb = true;
        } else if(climbSelfOther.getCheckedRadioButtonId() == selfOtherSucc.getId()) {
            selfClimb = true;
            otherClimb = true;
        } else if(climbOther.getCheckedRadioButtonId() == otherSucc.getId()) {
            selfClimb = true;
        }

        extras.putBoolean("DISCARD", messedUp.isChecked());
        extras.putBoolean("UNUSUAL", unusualMatch.isChecked());
        extras.putBoolean("TIPPED", robotTipped.isChecked());
        extras.putBoolean("DAMDRIVE", damagedDrivetrain.isChecked());
        extras.putBoolean("DAMINTAKE", damagedIntake.isChecked());
        extras.putBoolean("DAMLIFT", damagedLift.isChecked());
        extras.putBoolean("DEF", playedDefense.isChecked());
        extras.putBoolean("PUSH", pushBot.isChecked());
        extras.putBoolean("SELFCLIMB", selfClimb);
        extras.putBoolean("OTHERCLIMB", otherClimb);

        intent.putExtras(extras);
        startActivity(intent);
    }

    public void reset(View v){
        messedUp.setChecked(false);
        unusualMatch.setChecked(false);
        robotTipped.setChecked(false);
        damagedLift.setChecked(false);
        damagedDrivetrain.setChecked(false);
        damagedIntake.setChecked(false);
        playedDefense.setChecked(false);
        pushBot.setChecked(false);
        climbSelf.clearCheck();
        climbSelfOther.clearCheck();
        climbOther.clearCheck();
    }

    public void climbRadioLogic(View v){
        if (climbSelf.getCheckedRadioButtonId() == v.getId()) {
            climbSelfOther.clearCheck();
            climbOther.clearCheck();
        } else if(climbSelfOther.getCheckedRadioButtonId() == v.getId()) {
            climbSelf.clearCheck();
            climbOther.clearCheck();
        } else if(climbOther.getCheckedRadioButtonId() == v.getId()) {
            climbSelf.clearCheck();
            climbSelfOther.clearCheck();
        }
    }
}
