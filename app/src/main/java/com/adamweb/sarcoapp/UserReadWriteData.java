package com.adamweb.sarcoapp;

public class UserReadWriteData {
    String userFirstName;
    String userLastName;
    String userAdmissionNo;
    String userPhoneNo;

    public UserReadWriteData(String firstName, String lastName, String phoneNo, String admissionNo) {
        this.userFirstName = firstName;
        this.userLastName = lastName;
        this.userPhoneNo = phoneNo;
        this.userAdmissionNo = admissionNo;
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

    public String getUserAdmissionNo() {
        return userAdmissionNo;
    }

    public void setUserAdmissionNo(String userAdmissionNo) {
        this.userAdmissionNo = userAdmissionNo;
    }


}
