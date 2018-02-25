package com.example.craig.bobscout;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;

public class ViewMatches extends AppCompatActivity {

    TextView matchesView;
    String matchList;
    HashMap<String, ArrayList<String>> matches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_matches);
        matchesView = findViewById(R.id.matches);
        matchList = "";
        matches = new HashMap<String, ArrayList<String>>();

        File dir = new File(Environment.getExternalStorageDirectory(), "/BOBScout/");
        File[] files = dir.listFiles();


        for(File f : files) {
            String[] parts = f.getName().split("[_.]");
            String match = parts[0];
            String team = parts[1];

            if(!matches.keySet().contains(match)) {
                matches.put(match, new ArrayList<String>());
            }
            matches.get(match).add(team);

        }

        for(int i = 0; i < 100; i++) {
            matchList += "Match " + i + ": ";

            if(matches.keySet().contains(String.valueOf(i))) {
                for (String team : matches.get(String.valueOf(i))) {
                    matchList += team + ", ";
                }
            }
            matchList += "\n";
        }

        matchesView.setText(matchList);
    }
}
