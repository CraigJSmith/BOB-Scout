package com.example.craig.myapplication2;

/**
 * Created by craig on 1/21/2018.
 */

public class Action {

    String type;
    long time;

    public Action(String type, long time) {
        this.type = type;
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public String getType() {
        return type;
    }
}
