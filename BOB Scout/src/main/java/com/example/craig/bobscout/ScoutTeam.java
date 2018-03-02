package com.example.craig.bobscout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class ScoutTeam extends AppCompatActivity {

    EditText teamNum;
    EditText teamName;
    EditText teamAge;
    EditText driveTrain;
    RadioGroup climb;
    RadioGroup climbOthers;
    Button submit;

    boolean robotClimb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scout_team);

        teamNum = findViewById(R.id.teamNum);
        teamName = findViewById(R.id.teamName);
        teamAge = findViewById(R.id.teamAge);
        driveTrain = findViewById(R.id.driveTrain);
        climb = findViewById(R.id.climb);
        climbOthers = findViewById(R.id.climbOthers);
        submit = findViewById(R.id.submit);
        View selectedView = (View)findViewById( selectedID);
    }

    public void submit(View v) {
        Intent intent = new Intent(this, SubmitTeam.class);
        Bundle extras = new Bundle();

        extras.putString("TEAM_NUM", teamNum.getText().toString());
        extras.putString("TEAM_NAME", teamName.getText().toString());
        extras.putString("TEAM_AGE", teamAge.getText().toString());
        extras.putString("DRIVE_TRAIN", driveTrain.getText().toString());

        intent.putExtras(extras);
        startActivity(intent);
    }
}
