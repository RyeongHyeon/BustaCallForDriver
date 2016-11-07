package com.example.user.bustacallfordriver.model;

/**
 * Created by user on 2016-11-01.
 */
public class BusImageUrl {

    public int id;
    public String url;

    public BusImageUrl(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}