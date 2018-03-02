package com.example.craig.bobscout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
    }

    public void scoutMatch(View v) {
        Intent intent = new Intent(this, ScoutMatchSetup.class);
        startActivity(intent);
    }

    public void viewMatches(View v) {
        Intent intent = new Intent(this, ViewMatches.class);
        startActivity(intent);
    }

    public void scoutTeam(View v) {
        Intent intent = new Intent(this, ScoutTeam.class);
        startActivity(intent);
    }

    public void viewTeams(View v) {
        Intent intent = new Intent(this, ViewTeams.class);
        startActivity(intent);
    }
}
