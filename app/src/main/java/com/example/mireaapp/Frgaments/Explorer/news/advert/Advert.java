package com.example.mireaapp.Frgaments.Explorer.news.advert;

import java.io.Serializable;

public class Advert implements Serializable {

    private String title, description, dateTime, color;
    private int imageUrl;

    public Advert(String title, String description, String dateTime, String color, int imageUrl) {
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
        this.color = color;
        this.imageUrl = imageUrl;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }
}
