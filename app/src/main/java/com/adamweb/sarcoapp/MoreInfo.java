package com.adamweb.sarcoapp;

public class MoreInfo {
    String department, bestFriend, bestCourse, skills;

    public MoreInfo (){

    }
    public MoreInfo(String department, String bestFriend, String bestCourse, String skills) {
        this.department = department;
        this.bestFriend = bestFriend;
        this.bestCourse = bestCourse;
        this.skills = skills;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getBestFriend() {
        return bestFriend;
    }

    public void setBestFriend(String bestFriend) {
        this.bestFriend = bestFriend;
    }

    public String getBestCourse() {
        return bestCourse;
    }

    public void setBestCourse(String bestCourse) {
        this.bestCourse = bestCourse;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
