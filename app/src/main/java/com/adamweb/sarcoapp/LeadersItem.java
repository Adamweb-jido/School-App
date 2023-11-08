package com.adamweb.sarcoapp;

public class LeadersItem {

    String leaderName;
    int leaderImage;

    public LeadersItem(String leaderName, int leaderImage) {
        this.leaderName = leaderName;
        this.leaderImage = leaderImage;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public int getLeaderImage() {
        return leaderImage;
    }

    public void setLeaderImage(int leaderImage) {
        this.leaderImage = leaderImage;
    }
}
