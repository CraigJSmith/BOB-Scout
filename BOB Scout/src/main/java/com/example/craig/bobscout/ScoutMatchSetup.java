package com.example.craig.bobscout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

public class ScoutMatchSetup extends AppCompatActivity {

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    private EditText matchNumBox;
    private EditText teamNumBox;
    private RadioButton redLeftBlueRight;
    private RadioButton redRightBlueLeft;
    private String matchNum;
    private String teamNum;
    private String[] matches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scout_setup);

        sharedPref = this.getSharedPreferences("BOBScout_prefs", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        matchNumBox = findViewById(R.id.matchNumBox);
        teamNumBox = findViewById(R.id.teamNumBox);

        redLeftBlueRight = findViewById(R.id.redLeft);
        redRightBlueLeft = findViewById(R.id.redRight);

        matchNumBox.setText(String.valueOf(sharedPref.getInt("match", 1)));
        matchNum = matchNumBox.getText().toString();

        File schedule = new File(Environment.getExternalStorageDirectory(), "/BOBScout/schedule.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(schedule));
            String line = br.readLine();
            matches = line.split(",");
            teamNumBox.setText(matches[(Integer.valueOf(matchNum) - 1)]);
        } catch (Exception e) {
            teamNumBox.setText("");
        }

        populateTeam(null);

        if (!sharedPref.getBoolean("redLeft", true)) {
            redLeftBlueRight.setChecked(false);
            redRightBlueLeft.setChecked(true);
        } else {
            redLeftBlueRight.setChecked(true);
            redRightBlueLeft.setChecked(false);
        }

        teamNumBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //populateTeam(null);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //populateTeam(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //populateTeam(null);
            }
        });

        matchNumBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               // populateTeam(null);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                populateTeam(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //populateTeam(null);
            }
        });
    }

    public void submit(View v) {
        matchNum = matchNumBox.getText().toString();
        teamNum = teamNumBox.getText().toString();
        editor.putInt("match", Integer.parseInt(matchNum) + 1);

        File dir = new File(Environment.getExternalStorageDirectory(), "/BOBScout/Matches/");
        File[] files = dir.listFiles();

        if (files == null) {
            begin();
        } else if (matchNum.equals("") || teamNum.equals("")) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("No match/team entered");
            alertDialog.setMessage("You must enter a team and match number!");

            alertDialog.setButton(Dialog.BUTTON_NEUTRAL, "OKIE SRY", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // yell at user
                }
            });
            alertDialog.show();
        } else if (Arrays.asList(files).contains(new File(Environment.getExternalStorageDirectory().toString() + "/BOBScout/Matches/" + matchNum + "_" + teamNum + ".csv"))) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Overwrite match?");
            alertDialog.setMessage("This match has already been scouted. Are you sure you want to redo it?");

            alertDialog.setButton(Dialog.BUTTON_POSITIVE, "Yes, redo", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    begin();
                }
            });

            alertDialog.setButton(Dialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // yell at user
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

        try {
            RadioButton redLeft = findViewById(R.id.redLeft);
            editor.putBoolean("redLeft", redLeft.isChecked());
        } catch (NullPointerException e) {
            editor.putBoolean("redLeft", true);
        }
        editor.apply();

        intent.putExtras(extras);
        startActivity(intent);
    }

    public void viewMatches(View v) {
        Intent intent = new Intent(this, ViewMatches.class);
        startActivity(intent);
    }

    public void populateTeam(View v) {
        String match = matchNumBox.getText().toString();
        try {
            if (matches.length < Integer.parseInt(match)) {
                teamNum = "";
            } else {
                try {
                    teamNum = matches[Integer.parseInt(match) - 1];
                } catch (NumberFormatException e) {

                }
            }
            teamNumBox.setText(teamNum);
        } catch(NumberFormatException e) {
            
        }
    }
}
