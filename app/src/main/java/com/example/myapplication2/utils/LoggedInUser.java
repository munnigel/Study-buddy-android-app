package com.example.myapplication2.utils;

import static com.example.myapplication2.utils.Utils.getProfileID;

import com.google.firebase.firestore.DocumentReference;

public class LoggedInUser {

    private static LoggedInUser ourInstance = null;
    DocumentReference documentReference;
    String username;

    public static LoggedInUser getInstance() {
        if (ourInstance == null)
            ourInstance = new LoggedInUser();
        return ourInstance;
    }

    private LoggedInUser () {
    }

    public void setUser(DocumentReference documentReference, String username) {
        this.documentReference = documentReference;
        this.username = username;
    }

    public DocumentReference getUserDocRef() {
        return this.documentReference;
    }

    public String getUserId() {
      return getProfileID(this.documentReference.getPath());
    };

    public String getUserString() {
        return this.username;
    }
}