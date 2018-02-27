package com.example.craig.bobscout;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.webkit.WebView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import static java.util.Collections.max;

public class ViewMatches extends AppCompatActivity {

    WebView display;
    String matchList;
    HashMap<Integer, ArrayList<String>> matches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_matches);
        //matchesView = findViewById(R.id.matches);
        matchList = "";
        matches = new HashMap<Integer, ArrayList<String>>();
        display = (WebView) findViewById(R.id.display);

        File dir = new File(Environment.getExternalStorageDirectory(), "/BOBScout/Matches/");
        File[] files = dir.listFiles();

        if(files != null) {
            for (File f : files) {
                String[] parts = f.getName().split("[_.]");
                Integer match = Integer.parseInt(parts[0]);
                String team = parts[1];

                if (!matches.keySet().contains(match)) {
                    matches.put(match, new ArrayList<String>());
                }
                matches.get(match).add(team);

            }
        }

        matchList += "<style>table { border-collapse: collapse } table, th, td { border: 1px solid black; text-align: center }</style>";
        if(!matches.isEmpty()) {
            matchList += "<table style=\"width:100%\"> <tr> <th>Match</th> <th>Teams</th> </tr>";
            for (int i = 1; i <= max(matches.keySet()); i++) {
                matchList += "<tr> <td>" + i + "</td> <td>";
                if (matches.keySet().contains(i)) {
                    for (String team : matches.get(i)) {
                        matchList += team;
                        if (matches.get(i).size() > 1) {
                            matchList += ", ";
                        }
                    }
                }
                matchList += "</td> </tr>";
            }
            matchList += "</table>";
        } else {
            matchList += "No match data on device";
        }
        display.loadData(matchList, "text/html", null);
    }
}
