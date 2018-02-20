package com.example.craig.bobscout;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Craig on 2/20/2018.
 */

@Entity(tableName = "action")
public class ActionEntity {

    @PrimaryKey(autoGenerate = true)
    private int actionId;

    @ColumnInfo(name = "action_type")
    private String actionType;

    @ColumnInfo(name = "action_time")
    private double actionTime;

    public int getActionID() {
        return actionId;
    }

    public void setActionId(int id) {
        this.actionId = id;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String name) {
        this.actionType = name;
    }

    public double getActionTime() {
        return actionTime;
    }

    public void setActionTime(double time) {
        this.actionTime = time;
    }

}