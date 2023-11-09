package com.adamweb.sarcoapp;

public class ChatList {
    String name, time, lastMessage;
    int image;

    public ChatList(String name, String time, String lastMessage, int image) {
        this.name = name;
        this.time = time;
        this.lastMessage = lastMessage;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
