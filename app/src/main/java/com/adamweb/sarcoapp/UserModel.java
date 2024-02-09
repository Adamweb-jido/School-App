package com.adamweb.sarcoapp;

import android.net.Uri;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class UserModel {
    String userFirstName, userLastName, userPhoneNo, userAdmissionNo, userCombination, userComment, userImageUri, userUid;

    public UserModel(){

    }
    public UserModel(String userFirstName, String userLastName, String userPhoneNo, String userAdmissionNo, String userCombination, String userComment, String userImageUri, String userUid) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userPhoneNo = userPhoneNo;
        this.userAdmissionNo = userAdmissionNo;
        this.userCombination = userCombination;
        this.userComment = userComment;
        this.userImageUri = userImageUri;
        this.userUid = userUid;
    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("imageUri", userImageUri);
        return result;
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

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }
}
