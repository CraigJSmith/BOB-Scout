package com.example.craig.bobscout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
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
