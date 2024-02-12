package com.adamweb.sarcoapp;

import android.net.Uri;

public class LeaderItem {
    Uri imageUri;
    String name;

    public LeaderItem(Uri imageUri, String name) {
        this.imageUri = imageUri;
        this.name = name;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
