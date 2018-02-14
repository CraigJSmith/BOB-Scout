package com.example.craig.bobscout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class Submit extends AppCompatActivity {

    private TextView dataOutput;
    private String data;
    private String matchNum;
    private String teamNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        dataOutput = (TextView) findViewById(R.id.dataOutput);
        dataOutput.setMovementMethod(new ScrollingMovementMethod());

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        matchNum = extras.getString("EXTRA_MATCH");
        teamNum = extras.getString("EXTRA_TEAM");
        data = extras.getString("EXTRA_DATA");

        dataOutput.setText(data);
    }

    @Override
    public void onBackPressed() {
        Intent newMatch = new Intent(this, ScoutSetup.class);
        startActivity(newMatch);
    }
}
