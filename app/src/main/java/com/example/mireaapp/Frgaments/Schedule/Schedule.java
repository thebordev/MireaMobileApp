package com.example.mireaapp.Frgaments.Schedule;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "schedules")
public class Schedule implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "group")
    private String group;

    @ColumnInfo(name = "json_data")
    private String jsonData;

    @ColumnInfo(name = "last_update")
    private long lastUpdate;

    @ColumnInfo(name = "last_server_update")
    private long lastServerUpdate;

    public Schedule(String group, String jsonData, long lastUpdate, long lastServerUpdate){
        this.group = group;
        this.jsonData = jsonData;
        this.lastUpdate = lastUpdate;
        this.lastServerUpdate = lastServerUpdate;
    }

    public String getGroup() {
        return group;
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public void setLastServerUpdate(long lastServerUpdate) {
        this.lastServerUpdate = lastServerUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
