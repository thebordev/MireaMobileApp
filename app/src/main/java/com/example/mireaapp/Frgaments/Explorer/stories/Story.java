package com.example.mireaapp.Frgaments.Explorer.stories;

public class Story {

    private String imageUrl;
    private long timestart;
    private long timeend;
    private String username;

    public Story(String username) {
        this.imageUrl = imageUrl;
        this.timestart = timestart;
        this.timeend = timeend;
        this.username = username;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public long getTimestart() {
        return timestart;
    }

    public void setTimestart(long timestart) {
        this.timestart = timestart;
    }

    public long getTimeend() {
        return timeend;
    }

    public void setTimeend(long timeend) {
        this.timeend = timeend;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
