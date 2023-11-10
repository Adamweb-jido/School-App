package com.adamweb.sarcoapp;

public class AlbumItem {
    String name;
    String email;
    String combination;
    String phoneNumber;
    int image;

    public AlbumItem(String name, String email, String combination, String phoneNumber, int image) {
        this.name = name;
        this.image = image;
        this.email = email;
        this.combination = combination;
        this.phoneNumber = phoneNumber;
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


    public String getCombination() {
        return combination;
    }

    public void setCombination(String combination) {
        this.combination = combination;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
