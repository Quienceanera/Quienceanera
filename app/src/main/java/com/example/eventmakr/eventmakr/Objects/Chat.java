package com.example.eventmakr.eventmakr.Objects;

public class Chat {

    private String text;
    private String user;
    private String photoUrl;
    private String chatKey;
    private String uid;
    private String timestamp;

    public Chat(String text, String user, String photoUrl, String chatKey, String uid, String timestamp) {
        this.text = text;
        this.user = user;
        this.photoUrl = photoUrl;
        this.chatKey = chatKey;
        this.uid = uid;
        this.timestamp = timestamp;
    }

    public Chat() {
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

    public String getChatKey() {
        return chatKey;
    }

    public void setChatKey(String chatKey) {
        this.chatKey = chatKey;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
