package com.example.mireaapp.Frgaments.Explorer.news;

import java.io.Serializable;

public class News implements Serializable {

    private String title, description, dateTime, color;
    private int imageUrl;

    public News(String title, String description, String dateTime, int imageUrl, String color) {
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
        this.imageUrl = imageUrl;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
