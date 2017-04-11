package com.example.eventmakr.eventmakr.Objects;

public class Message {

    private String text;
    private String user;
    private String photoUrl;
    private String eventKey;
    private String senderUid;
    private String receiverUid;
    private String timestamp;

    public Message() {
    }

    public Message(String text, String user, String photoUrl, String eventKey, String senderUid, String receiverUid, String timestamp) {
        this.text = text;
        this.user = user;
        this.photoUrl = photoUrl;
        this.eventKey = eventKey;
        this.senderUid = senderUid;
        this.receiverUid = receiverUid;
        this.timestamp = timestamp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getSenderUid() {
        return senderUid;
    }

    public void setSenderUid(String senderUid) {
        this.senderUid = senderUid;
    }

    public String getReceiverUid() {
        return receiverUid;
    }

    public void setReceiverUid(String receiverUid) {
        this.receiverUid = receiverUid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
