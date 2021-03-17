package com.example.mireaapp.Frgaments.Explorer.news;

import java.io.Serializable;

public class News implements Serializable {

    private String title, description, dateTime, color;
    private int imageNews, imageAuthor;

    public News(String title, String description, String dateTime, String color, int imageNews, int imageAuthor) {
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
        this.color = color;
        this.imageNews = imageNews;
        this.imageAuthor = imageAuthor;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getColor() {
        return color;
    }

    public int getImageNews() {
        return imageNews;
    }

    public int getImageAuthor() {
        return imageAuthor;
    }
}
