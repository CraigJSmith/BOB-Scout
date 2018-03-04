package com.example.craig.bobscout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

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
        driveTrain = findViewById(R.id.driveTrain);
        climb = findViewById(R.id.climb);
        climbOthers = findViewById(R.id.climbOthers);
        submit = findViewById(R.id.submit);
    }

    public void submit(View v) {
        if(teamNum.getText().toString().equals("")) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("No team number");
            alertDialog.setMessage("You must enter a team number.");
            alertDialog.setButton(Dialog.BUTTON_NEUTRAL,"OKIE SORRY", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    //nothing
                }
            });
            alertDialog.show();
        } else {
            Intent intent = new Intent(this, SubmitTeam.class);
            Bundle extras = new Bundle();

            extras.putString("TEAM_NUM", teamNum.getText().toString());
            extras.putString("TEAM_NAME", teamName.getText().toString());
            extras.putString("DRIVE_TRAIN", driveTrain.getText().toString());

            try {
                RadioButton climbStatus = climb.findViewById(climb.getCheckedRadioButtonId());
                extras.putString("CLIMB", climbStatus.getText().toString());
            } catch(NullPointerException e) {
                extras.putString("CLIMB", "na");
            }

            intent.putExtras(extras);
            startActivity(intent);
        }
    }
}
