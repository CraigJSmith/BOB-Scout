package com.example.craig.bobscout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ScoutAuto extends AppCompatActivity {

    private ToggleButton autoCross;
    private ToggleButton cubeSwitch;
    private ToggleButton cubeScale;
    private ToggleButton cubePickup;
    private TextView currentlyScouting;
    private RadioGroup autoStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scout_auto);

        autoCross = findViewById(R.id.autoCross);
        cubeSwitch = findViewById(R.id.cubeSwitch);
        cubeScale = findViewById(R.id.cubeScale);
        cubePickup = findViewById(R.id.cubePickup);
        currentlyScouting = findViewById(R.id.currentlyScouting);
        autoStart = findViewById(R.id.autoStart);

        Bundle extras = getIntent().getExtras();
        currentlyScouting.setText("You are scouting team " + extras.get("TEAM") + ", match " + extras.get("MATCH"));
    }

    public void startTeleop(View v) {
        Intent intent = new Intent(this, ScoutMatch.class);
        Bundle extras = getIntent().getExtras();

        try {
            RadioButton climbStatus = autoStart.findViewById(autoStart.getCheckedRadioButtonId());
            extras.putString("AUTO_START", climbStatus.getText().toString());
        } catch(NullPointerException e) {
            extras.putString("AUTO_START", "null");
        }

        extras.putBoolean("AUTO_CROSS", autoCross.isChecked());
        extras.putBoolean("AUTO_SWITCH", cubeSwitch.isChecked());
        extras.putBoolean("AUTO_SCALE", cubeScale.isChecked());
        extras.putBoolean("AUTO_PICKUP", cubePickup.isChecked());
        intent.putExtras(extras);

        startActivity(intent);
    }
}
