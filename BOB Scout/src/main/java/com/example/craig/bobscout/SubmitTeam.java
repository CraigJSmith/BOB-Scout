package com.example.craig.bobscout;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SubmitTeam extends AppCompatActivity {

    private TextView outputBox;
    private String teamNum;
    private String teamName;
    private String teamAge;
    private String driveTrain;
    private String climb;
    private String output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_team);
        outputBox = findViewById(R.id.output);

        Bundle extras = getIntent().getExtras();
        teamNum = extras.getString("TEAM_NUM");
        teamName = extras.getString("TEAM_NAME");
        climb = extras.getString("CLIMB");

        output = teamNum + "," + teamName + "," + climb + ",";

        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File dir = new File(Environment.getExternalStorageDirectory(), "/BOBScout/Teams/");
            dir.mkdirs();
            File file = new File(dir, teamNum + ".csv");

            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file, false);
                fileOutputStream.write(output.getBytes());
                fileOutputStream.close();
                Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_LONG).show();
            }

        }
        outputBox.setText(output);
    }

    @Override
    public void onBackPressed() {
        Intent newMatch = new Intent(this, ScoutTeam.class);
        startActivity(newMatch);
    }

}
