package com.example.mireaapp.Frgaments.Schedule.Data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.mireaapp.Frgaments.Schedule.Models.ScheduleItem;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "schedules")
public class Schedule implements Serializable {
    @PrimaryKey()
    @NonNull
    private String groupKey;

    @ColumnInfo(name = "group")
    private String group;

    @ColumnInfo(name = "json_data")
    private String jsonData;

    @ColumnInfo(name = "last_update")
    private long lastUpdate;

    @ColumnInfo(name = "last_server_update")
    private long lastServerUpdate;

    public static ArrayList<ScheduleItem> scheduleItems;

    public Schedule(String group, String jsonData, long lastUpdate, long lastServerUpdate){
        this.group = group;
        this.groupKey = this.group;
        this.jsonData = jsonData;
        this.lastUpdate = lastUpdate;
        this.lastServerUpdate = lastServerUpdate;
    }

    public String getGroup() {
        return group;
    }

    public long getLastServerUpdate() {
        return lastServerUpdate;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public void setLastServerUpdate(long lastServerUpdate) {
        this.lastServerUpdate = lastServerUpdate;
    }

    public String getGroupKey() { return groupKey; }

    public void setGroupKey(String groupKey) { this.groupKey = groupKey; }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
