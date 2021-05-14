package com.example.mireaapp.Frgaments.Profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GroupsList {

    @SerializedName("groups")
    @Expose
    private ArrayList<String> groups = new ArrayList<>();

    public ArrayList<String> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<String> groups) {
        this.groups = groups;
    }
}
