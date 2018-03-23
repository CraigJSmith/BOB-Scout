package com.example.craig.bobscout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Settings extends AppCompatActivity {

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private RadioGroup alliance;
    private RadioButton red1;
    private RadioButton red2;
    private RadioButton red3;
    private RadioButton blue1;
    private RadioButton blue2;
    private RadioButton blue3;
    private String allianceToScout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedPref = this.getSharedPreferences("BOBScout_prefs", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        alliance = findViewById(R.id.alliance);
        red1 = findViewById(R.id.red1);
        red2 = findViewById(R.id.red2);
        red3 = findViewById(R.id.red3);
        blue1 = findViewById(R.id.blue1);
        blue2 = findViewById(R.id.blue2);
        blue3 = findViewById(R.id.blue3);

       allianceToScout = sharedPref.getString("allianceToScout", "");

        if(allianceToScout.equals("red1")) {
            red1.setChecked(true);
        } else if(allianceToScout.equals("red2")) {
            red2.setChecked(true);
        } else if(allianceToScout.equals("red3")) {
            red3.setChecked(true);
        } else if(allianceToScout.equals("blue1")) {
            blue1.setChecked(true);
        } else if(allianceToScout.equals("blue2")) {
            blue2.setChecked(true);
        } else if(allianceToScout.equals("blue3")) {
            blue3.setChecked(true);
        }

    }

    public void saveSettings(View v) {
        int id = alliance.getCheckedRadioButtonId();
        if(id == red1.getId()) {
            editor.putString("allianceToScout", "red1");
        } else if(id == red2.getId()) {
            editor.putString("allianceToScout", "red2");
        } else if(id == red3.getId()) {
            editor.putString("allianceToScout", "red3");
        } else if(id == blue1.getId()) {
            editor.putString("allianceToScout", "blue1");
        } else if(id == blue2.getId()) {
            editor.putString("allianceToScout", "blue2");
        } else if(id == blue3.getId()) {
            editor.putString("allianceToScout", "blue3");
        }
        editor.apply();

        Intent intent = new Intent(this, ScoutMatchSetup.class);
        startActivity(intent);
    }
}