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
import java.io.FileOutputStream;
import java.io.IOException;

public class Submit extends AppCompatActivity {

    private TextView dataOutput;
    private boolean autoLine;
    private boolean autoCubeSwitch;
    private boolean autoCubeScale;
    private boolean autoCubePickup;

    private String data;
    private String matchNum;
    private String teamNum;

    private String output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        dataOutput = (TextView) findViewById(R.id.dataOutput);
        dataOutput.setMovementMethod(new ScrollingMovementMethod());

        Bundle extras = getIntent().getExtras();

        autoLine = extras.getBoolean("EXTRA_AUTO_CROSS");
        autoCubeSwitch = extras.getBoolean("EXTRA_AUTO_SWITCH");
        autoCubeScale = extras.getBoolean("EXTRA_AUTO_SCALE");
        autoCubePickup = extras.getBoolean("EXTRA_AUTO_PICKUP");

        matchNum = extras.getString("EXTRA_MATCH");
        teamNum = extras.getString("EXTRA_TEAM");
        data = extras.getString("EXTRA_DATA");

        output = matchNum + "@" + teamNum + "," + autoLine + ":" + autoCubeSwitch + ":" + autoCubeScale + ":" + autoCubePickup + "," + data;

        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File dir = new File(Environment.getExternalStorageDirectory(), "/BOBScout");
            dir.mkdirs();
            File file = new File(dir, matchNum + "_" + teamNum + ".csv");

            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file, false);
                fileOutputStream.write(output.getBytes());
                fileOutputStream.close();
                Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_LONG).show();
                Log.d("BOBScout", e.getMessage());
            }

        }
        dataOutput.setText(output);
    }

    @Override
    public void onBackPressed() {
        Intent newMatch = new Intent(this, ScoutMatchSetup.class);
        startActivity(newMatch);
    }
}
