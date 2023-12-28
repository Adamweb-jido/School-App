package com.adamweb.sarcoapp;

public class UserReadWriteData {
    String userLastName;
    String userAdmissionNo;
    String userPhoneNo;

    public UserReadWriteData(String lastName, String phoneNo, String admissionNo) {
        this.userLastName = lastName;
        this.userPhoneNo = phoneNo;
        this.userAdmissionNo = admissionNo;
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
