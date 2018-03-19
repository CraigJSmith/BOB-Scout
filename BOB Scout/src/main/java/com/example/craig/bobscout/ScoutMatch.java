package com.example.craig.bobscout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ScoutMatch extends Activity {

    private String matchNum;
    private String teamNum;
    private static long startTime;
    private long lastTime;
    private String data;
    private boolean redLeft;
    private ArrayList<Action> actions;
    private ArrayList<View> buttons, buttonsLeft, buttonsRight;
    private Button p_l1,ex_l,p_l2,cp_l,sw_l1,sw_l2,c_1,c_2,c_3,sc_1,fpickup,fdrop,sc_2,c_4,c_5,c_6,sw_r1,sw_r2,cp_r,p_r1,ex_r,p_r2,climb_l,climb_r;
    private View prevButton;
    private TextView timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scout_match);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        matchNum = extras.getString("MATCH");
        teamNum = extras.getString("TEAM");
        redLeft = extras.getBoolean("REDLEFT");
        startTime = System.currentTimeMillis();
        data = "";
        actions = new ArrayList<Action>();

        buttons = new ArrayList<View>();
        buttonsLeft = new ArrayList<View>();
        buttonsRight = new ArrayList<View>();

        timer = findViewById(R.id.timer);
        lastTime = System.currentTimeMillis();

        p_l1 = findViewById(R.id.p_l1);
        ex_l = findViewById(R.id.ex_l);
        p_l2 = findViewById(R.id.p_l2);

        cp_l = findViewById(R.id.cp_l);

        sw_l1 = findViewById(R.id.sw_l1);
        sw_l2 = findViewById(R.id.sw_l2);

        c_1 = findViewById(R.id.c_1);
        c_2 = findViewById(R.id.c_2);
        c_3 = findViewById(R.id.c_3);

        sc_1 = findViewById(R.id.sc_1);
        fpickup = findViewById(R.id.fpickup);
        fdrop = findViewById(R.id.fdrop);
        sc_2 = findViewById(R.id.sc_2);

        c_4 = findViewById(R.id.c_4);
        c_5 = findViewById(R.id.c_5);
        c_6 = findViewById(R.id.c_6);

        sw_r1 = findViewById(R.id.sw_r1);
        sw_r2 = findViewById(R.id.sw_r2);

        cp_r = findViewById(R.id.cp_r);

        p_r1 = findViewById(R.id.p_r1);
        ex_r = findViewById(R.id.ex_r);
        p_r2 = findViewById(R.id.p_r2);

        buttonsLeft.add(p_l1);
        buttonsLeft.add(ex_l);
        buttonsLeft.add(p_l2);
        buttonsLeft.add(sw_l1);
        buttonsLeft.add(sw_l2);

        buttons.add(c_1);
        buttons.add(c_2);
        buttons.add(c_3);
        buttons.add(sc_1);
        buttons.add(fpickup);
        buttons.add(fdrop);
        buttons.add(sc_2);
        buttons.add(c_4);
        buttons.add(c_5);
        buttons.add(c_6);

        buttonsRight.add(sw_r1);
        buttonsRight.add(sw_r2);
        buttonsRight.add(p_r1);
        buttonsRight.add(ex_r);
        buttonsRight.add(p_r2);

        final Handler handler=new Handler();
        handler.post(new Runnable(){
            @Override
            public void run() {
                double time = (((double)System.currentTimeMillis() - lastTime)/1000);
                BigDecimal cut = new BigDecimal(time);
                BigDecimal floored = cut.setScale(1, BigDecimal.ROUND_DOWN);

                timer.setText("Time: " + floored.toString());

                ToggleButton b = (ToggleButton) prevButton;
                if (b != null && b.isChecked()) {
                    timer.setTextColor(getResources().getColor(R.color.lime));
                } else {
                    timer.setTextColor(Color.WHITE);
                }

                handler.postDelayed(this,100);
            }
        });

        start();
    }

    @Override
    protected void onResume()
    {
        if(!actions.isEmpty()) {
            actions.remove(actions.size()-1); // to remove the 'stop' action
        }
        data = "";
        prevButton = null;
        super.onResume();
    }

    public void start() {
        setupButtons(redLeft);
        startTime = System.currentTimeMillis();
    }

    public void stop(View v) {
        addAction(v);
        saveData();

        Intent intent = new Intent(this, ScoutMatchEnd.class);
        Bundle extras = getIntent().getExtras();
        extras.putString("MATCH", matchNum);
        extras.putString("TEAM", teamNum);
        extras.putString("DATA", data);
        extras.putLong("STARTTIME", startTime);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void climb(View v) {
        long time = System.currentTimeMillis() - startTime;
        String action = v.getTag().toString();
        Action a = new Action(action, time);
        actions.add(a);

        saveData();

        Intent intent = new Intent(this, ScoutMatchClimb.class);
        Bundle extras = getIntent().getExtras();
        extras.putString("MATCH", matchNum);
        extras.putString("TEAM", teamNum);
        extras.putString("DATA", data);
        extras.putLong("STARTTIME", startTime);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void addAction(View v) {
        if(!v.equals(prevButton) && prevButton != null) {
            ((ToggleButton)prevButton).setChecked(false);
        }
        prevButton = v;

        long time = System.currentTimeMillis() - startTime;
        String action = v.getTag().toString();
        Action a = new Action(action, time);
        actions.add(a);
    }

    public void saveData() {
        Bundle extras = getIntent().getExtras();
        String match = extras.getString("MATCH");
        String team = extras.getString("TEAM");

        for (Action a : actions) {
            data += match + "," + team + ",";
            data += a.getType() + "," + a.getTime() + ",";
            data += "\n";
        }
    }

    public void setupButtons(boolean leftRed) {
        if(leftRed) {
            for(View v : buttonsLeft) {
                Button b = (Button) v;
                b.setBackground(getResources().getDrawable(R.drawable.redscoutbutton));
            }

            for(View v : buttonsRight) {
                Button b = (Button) v;
                b.setBackground(getResources().getDrawable(R.drawable.bluescoutbutton));
            }

            p_l1.setTag("p_r1");
            p_l2.setTag("p_r2");
            ex_l.setTag("ex_r");
            cp_l.setTag("cp_r");
            sw_l1.setTag("sw_r1");
            sw_l2.setTag("sw_r2");

            sw_r1.setTag("sw_b1");
            sw_r2.setTag("sw_b2");
            cp_r.setTag("cp_b");
            p_r1.setTag("p_b1");
            ex_r.setTag("ex_b");
            p_r2.setTag("p_rb2");

        } else {
            for(View v : buttonsLeft) {
                Button b = (Button) v;
                b.setBackground(getResources().getDrawable(R.drawable.bluescoutbutton));
            }

            for(View v : buttonsRight) {
                Button b = (Button) v;
                b.setBackground(getResources().getDrawable(R.drawable.redscoutbutton));
            }

            p_l1.setTag("p_b1");
            p_l2.setTag("p_b2");
            ex_l.setTag("ex_b");
            cp_l.setTag("cp_b");
            sw_l1.setTag("sw_b1");
            sw_l2.setTag("sw_b2");

            sw_r1.setTag("sw_r1");
            sw_r2.setTag("sw_r2");
            cp_r.setTag("cp_r");
            p_r1.setTag("p_r1");
            ex_r.setTag("ex_r");
            p_r2.setTag("p_r2");
        }
    }
}