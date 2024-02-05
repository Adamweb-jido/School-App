package com.adamweb.sarcoapp;

public class UserModel {
    String userUid, userFirstName, userLastName, userAdmissionNo, userPhoneNo, userCombination, userComment, userImageUri;

    public UserModel(){

    }
    public UserModel(String userUid, String userFirstName, String userLastName, String userAdmissionNo, String userPhoneNo, String userCombination, String userComment, String userImageUri) {
        this.userUid = userUid;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userAdmissionNo = userAdmissionNo;
        this.userPhoneNo = userPhoneNo;
        this.userCombination = userCombination;
        this.userComment = userComment;
        this.userImageUri = userImageUri;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
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

    public String getUserImageUri() {
        return userImageUri;
    }

    public void setUserImageUri(String userImageUri) {
        this.userImageUri = userImageUri;
    }
}
