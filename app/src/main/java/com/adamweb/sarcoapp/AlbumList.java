package com.adamweb.sarcoapp;

public class AlbumList {
    String name;
    String combination;
    String phoneNumber;
    int image;

    public AlbumList(String name, String combination, String phoneNumber, int image) {
        this.name = name;
        this.combination = combination;
        this.phoneNumber = phoneNumber;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


}
