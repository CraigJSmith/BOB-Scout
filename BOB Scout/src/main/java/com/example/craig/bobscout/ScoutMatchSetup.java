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
import android.widget.EditText;
import java.io.File;
import java.util.Arrays;

public class ScoutMatchSetup extends AppCompatActivity {

    private EditText matchNumBox;
    private EditText teamNumBox;
    private String matchNum;
    private String teamNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scout_setup);

        matchNumBox = findViewById(R.id.matchNumBox);
        teamNumBox = findViewById(R.id.teamNumBox);
    }

    public void submit(View v) {
        matchNum = matchNumBox.getText().toString();
        teamNum = teamNumBox.getText().toString();

        File dir = new File(Environment.getExternalStorageDirectory(), "/BOBScout/");
        File[] files = dir.listFiles();

        if(files == null || Arrays.asList(files).contains(new File(Environment.getExternalStorageDirectory().toString() + "/BOBScout/" + matchNum + "_" + teamNum + ".csv"))) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Overwrite match?");
            alertDialog.setMessage("This match has already been scouted. Are you sure you want to redo it?");

            alertDialog.setButton(Dialog.BUTTON_POSITIVE,"Yes, redo", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    begin();
                }
            });

            alertDialog.setButton(Dialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // here you can add functions
                }
            });
            alertDialog.show();
        } else {
            begin();
        }

    }

    private void begin() {
        Intent intent = new Intent(this, ScoutAuto.class);
        Bundle extras = new Bundle();
        extras.putString("MATCH", matchNum);
        extras.putString("TEAM", teamNum);
        intent.putExtras(extras);

        startActivity(intent);
    }

    public void viewMatches(View v) {
        Intent intent = new Intent(this, ViewMatches.class);
        startActivity(intent);
    }
}
