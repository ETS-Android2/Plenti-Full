package com.example.plenti_full.Javabeans;

import android.widget.ImageView;

public class Favorite {
    private String id;
    private String name;
    private String image;
    private ImageView imageView;

    public Favorite(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public Favorite(String id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }


    public void setImage(String image) {
        this.image = image;

    }

    public String getId() {
        return id;
    }
}
