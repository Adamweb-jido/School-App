package com.adamweb.sarcoapp;

public class UserReadWriteData {
    String userFirstName;
    String userLastName;
    String userAdmissionNo;
    String userPhoneNo;
    String userAdmissionNumber;
    String userCombination;
    String userComment;

    public UserReadWriteData(){

    }
    public UserReadWriteData(String firstName, String lastName, String phoneNo, String admissionNo, String combination, String comment) {
        this.userFirstName = firstName;
        this.userLastName = lastName;
        this.userPhoneNo = phoneNo;
        this.userAdmissionNumber = admissionNo;
        this.userCombination = combination;
        this.userComment = comment;
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

    public String getUserAdmissionNo() {
        return userAdmissionNo;
    }

    public void setUserAdmissionNo(String userAdmissionNo) {
        this.userAdmissionNo = userAdmissionNo;
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

}
