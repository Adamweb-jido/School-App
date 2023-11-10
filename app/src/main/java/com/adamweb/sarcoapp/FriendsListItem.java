package com.adamweb.sarcoapp;

public class FriendsListItem {
    String fName, comment;
    int imageCover;

    public FriendsListItem(String fName, String comment, int imageCover) {
        this.fName = fName;
        this.comment = comment;
        this.imageCover = imageCover;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getImageCover() {
        return imageCover;
    }

    public void setImageCover(int imageCover) {
        this.imageCover = imageCover;
    }
}
