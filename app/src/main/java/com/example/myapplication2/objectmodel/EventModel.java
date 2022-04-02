package com.example.myapplication2.objectmodel;

import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.DocumentReference;
import com.google.protobuf.StringValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/*
* Firebase Firestore Document Object Model for the Events Collection
* @ID documentId: string
*
* @field capacity: number
* @field description: string
* @field eventCreated: timestamp
* @field eventEnd: timestamp
* @field eventStart: timestamp
* @field imagePath: string referencing URL from Firebase Cloud Storage
* @field lastUpdated: timestamp
* @field module: DocumentReference from Modules Collection
* @field status: string
* @field title: string
* @field userCreated: DocumentReference from Users Collection
* @field userJoined: ArrayList of DocumentReference from Users Collection
*        @index: DocumentReference from Users Collection
* @field venue: DocumentReference from Venues Collection
*/
public class EventModel implements ObjectModel {

    public static final String TAG = "Event Model";
    public static final String COLLECTION_ID  = "Events";
    public static final ArrayList<String> STATUSES = new ArrayList<>(Arrays.asList("upcoming", "ongoing", "completed"));

    @DocumentId
    private String documentId;

    private int capacity;
    private String description;
    private Date eventEnd;
    private Date eventStart;
    private String imagePath;
    private DocumentReference module;
    private String status;
    private String title;
    private DocumentReference userCreated;
    private ArrayList<DocumentReference> userJoined;
    private String venue;

    public EventModel() {} //no arg constructor for firebase
    
    //FIXME To add String documentId into the constructor
    public EventModel(String title, String description, String venue, DocumentReference module,
                      int capacity, Date eventStart, Date eventEnd, String imagePath,
                      DocumentReference userCreated) {
        this.title = title;
        this.description = description;
        this.venue = venue;
        this.module = module;
        this.capacity = capacity;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.imagePath = imagePath;
        this.userCreated = userCreated;

        // Initialized as empty
        this.status = "upcoming";
        this.userJoined = new ArrayList<>(Arrays.asList(userCreated));
    }

//     public EventModel(String documentId, int capacity, String description, Date eventCreated,
//                         Date eventEnd, Date eventStart, String imagePath, Date lastUpdated,
//                         DocumentReference module, String status, String title,
//                         DocumentReference userCreated, ArrayList<DocumentReference> userJoined,
//                         String venue) {
//         this.documentId = documentId;
//         this.capacity = capacity;
//         this.description = description;
//         this.eventCreated = eventCreated;
//         this.eventEnd = eventEnd;
//         this.eventStart = eventStart;
//         this.imagePath = imagePath;
//         this.lastUpdated = lastUpdated;
//         this.module = module;
//         this.status = status;
//         this.title = title;
//         this.userCreated = userCreated;
//         this.userJoined = userJoined;
//         this.venue = venue;
//     }

    public static String getTAG() {
        return TAG;
    }

    public static String getCollectionId() {
        return COLLECTION_ID;
    }

    public static ArrayList<String> getStatuses() {
        return STATUSES;
    }

    @Override
    public String getDocumentId() {
        return documentId;
    }

    @Override
    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public DocumentReference getModule() {
        return module;
    }

    public void setModule(DocumentReference module) {
        this.module = module;
    }

    public String getCapacityString() {
        return String.valueOf(capacity);
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Date getEventStart() {
        return eventStart;
    }

    public void setEventStart(Date eventStart) {
        this.eventStart = eventStart;
    }

    public Date getEventEnd() {
        return eventEnd;
    }

    public void setEventEnd(Date eventEnd) {
        this.eventEnd = eventEnd;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DocumentReference getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(DocumentReference userCreated) {
        this.userCreated = userCreated;
    }

    public ArrayList<DocumentReference> getUserJoined() {
        return userJoined;
    }

    public void setUserJoined(ArrayList<DocumentReference> userJoined) {
        this.userJoined = userJoined;
    }

    @Override
    public String toString() {
        return "EventModel{" +
                "documentId='" + documentId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", module=" + module +
                ", venue=" + venue +
                ", capacity=" + capacity +
                ", eventStart=" + eventStart +
                ", eventEnd=" + eventEnd +
                ", imagePath=" + imagePath +
                ", status='" + status + '\'' +
                ", userCreated=" + userCreated +
                ", userJoined=" + userJoined +
                '}';
    }
}