package com.example.craig.bobscout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ScoutSetup extends AppCompatActivity {

    private EditText matchNumBox;
    private EditText teamNumBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scout_setup);

        matchNumBox = (EditText) findViewById(R.id.matchNumBox);
        teamNumBox = (EditText) findViewById(R.id.teamNumBox);
    }

    public void submit(View v) {
        Intent intent = new Intent(this, ScoutMatch.class);

        String matchNum = matchNumBox.getText().toString();
        String teamNum = teamNumBox.getText().toString();

        Bundle extras = new Bundle();
        extras.putString("EXTRA_MATCH", matchNum);
        extras.putString("EXTRA_TEAM", teamNum);
        intent.putExtras(extras);

        startActivity(intent);
    }
}
