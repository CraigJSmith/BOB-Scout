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
import java.util.HashSet;

import static java.util.Collections.max;

public class ViewTeams extends AppCompatActivity {

    WebView display;
    String matchList;
    HashSet<Integer> teams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_matches);

        display = (WebView) findViewById(R.id.display);
        matchList = "";
        teams = new HashSet<Integer>();

        File dir = new File(Environment.getExternalStorageDirectory(), "/BOBScout/Teams/");
        File[] files = dir.listFiles();

        if(files != null) {
            for (File f : files) {
                String[] parts = f.getName().split("[.]");
                Integer team = Integer.parseInt(parts[0]);
                teams.add(team);
            }
        }

        matchList += "<style>table { border-collapse: collapse } table, th, td { border: 1px solid black; text-align: center }</style>";

        if (!teams.isEmpty()) {
            matchList += "<table style=\"width:100%\"> <tr> <th>Teams</th> </tr>";

            for (Integer team : teams) {
                matchList += "<tr> <td>" + team.toString() + "</td>";
            }

        } else {
            matchList += "No team data on device";
        }
        matchList += "</table>";

        display.loadData(matchList, "text/html", null);
    }
}
