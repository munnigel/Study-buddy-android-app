package com.example.myapplication2.utils;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;


public abstract class FirebaseQuery {
    private static final String TAG = "FirebaseQuery";
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private void getData(String collectionId) {
        getQuery(collectionId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        callbackOnSuccess(queryDocumentSnapshots);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Error getting documents: ", e);
                    }
                });
    }

    public abstract void callbackOnSuccess(QuerySnapshot queryDocumentSnapshots);

    public Query getQuery(String collectionId) {
        return getDb().collection(collectionId);
    }

    protected FirebaseFirestore getDb() {
        return this.db;
    }

    public void run(String collectionId) {
        getData(collectionId);
    }
}
