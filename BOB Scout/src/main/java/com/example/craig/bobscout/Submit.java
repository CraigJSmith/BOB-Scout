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

        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File dir = new File(Environment.getExternalStorageDirectory(), "/BOBScout");
            dir.mkdirs();
            File file = new File(dir, "Match.csv");

            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                fileOutputStream.write(data.getBytes());
                fileOutputStream.close();
                Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_LONG).show();
                Log.d("BOBScout", e.getMessage());
            }

        }
        dataOutput.setText(data);
    }

    @Override
    public void onBackPressed() {
        Intent newMatch = new Intent(this, ScoutSetup.class);
        startActivity(newMatch);
    }
}
