package com.adamweb.sarcoapp;

public class UserReadWriteData {
    String firstName, lastName, admissionNumber, phoneNumber;

    public UserReadWriteData(String firstName, String lastName, String admissionNumber, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.admissionNumber = admissionNumber;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAdmissionNumber() {
        return admissionNumber;
    }

    public void setAdmissionNumber(String admissionNumber) {
        this.admissionNumber = admissionNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
