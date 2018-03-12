package com.example.safaa.tolkappen;


import java.io.Serializable;

/**
 * Created by nikol on 2018-03-12.
 */

public class Pages implements Serializable {
    private String url;

    public Pages(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return url;
    }
}
