package com.adamweb.sarcoapp;

public class Item {
    String name;
    String email;
    int image;

    public Item(String name, String email, int image) {
        this.name = name;
        this.image = image;
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
