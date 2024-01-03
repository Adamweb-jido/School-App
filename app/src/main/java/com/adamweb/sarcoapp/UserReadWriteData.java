package com.adamweb.sarcoapp;

public class UserReadWriteData {
    String userFirstName;
    String userLastName;
    String userAdmissionNo;
    String userPhoneNo;
    String userAdmissionNumber;
    String userCombination;
    String userComment;
    int userProfileImage;

    public UserReadWriteData(String firstName, String lastName, String phoneNo) {
        this.userFirstName = firstName;
        this.userLastName = lastName;
        this.userPhoneNo = phoneNo;
    }

    public UserReadWriteData(String admissionNo, String combination, String comment, int profileImage) {
        this.userAdmissionNumber = admissionNo;
        this.userCombination = combination;
        this.userComment = comment;
        this.userProfileImage = profileImage;
    }

    public UserReadWriteData(){

    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserPhoneNo() {
        return userPhoneNo;
    }

    public void setUserPhoneNo(String userPhoneNo) {
        this.userPhoneNo = userPhoneNo;
    }

    public String getUserAdmissionNumber() {
        return userAdmissionNumber;
    }

    public void setUserAdmissionNumber(String userAdmissionNumber) {
        this.userAdmissionNumber = userAdmissionNumber;
    }

    public String getUserCombination() {
        return userCombination;
    }

    public void setUserCombination(String userCombination) {
        this.userCombination = userCombination;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public int getUserProfileImage() {
        return userProfileImage;
    }

    public void setUserProfileImage(int userProfileImage) {
        this.userProfileImage = userProfileImage;
    }

}
