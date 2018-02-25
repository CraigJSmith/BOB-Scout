package com.example.craig.bobscout;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import static java.util.Collections.max;

public class ViewMatches extends AppCompatActivity {

    TextView matchesView;
    String matchList;
    HashMap<Integer, ArrayList<String>> matches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_matches);
        matchesView = findViewById(R.id.matches);
        matchList = "";
        matches = new HashMap<Integer, ArrayList<String>>();

        File dir = new File(Environment.getExternalStorageDirectory(), "/BOBScout/");
        File[] files = dir.listFiles();

        for(File f : files) {
            String[] parts = f.getName().split("[_.]");
            Integer match = Integer.parseInt(parts[0]);
            String team = parts[1];

            if(!matches.keySet().contains(match)) {
                matches.put(match, new ArrayList<String>());
            }
            matches.get(match).add(team);

        }

        for(int i = 1; i <= max(matches.keySet()); i++) {
            matchList += "<b>Match " + i + ": </b>";

            if(matches.keySet().contains(i)) {
                for (String team : matches.get(i)) {
                    matchList += team + "  ";
                }

            }
            matchList += "<br></br>";
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            matchesView.setText(Html.fromHtml(matchList,Html.FROM_HTML_MODE_LEGACY));
        } else {
            matchesView.setText(Html.fromHtml(matchList));
        }
    }
}
