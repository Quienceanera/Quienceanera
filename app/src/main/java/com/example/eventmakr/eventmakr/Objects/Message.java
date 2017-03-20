package com.example.eventmakr.eventmakr.Objects;

public class Message {

    private String text;
    private String user;
    private String photoUrl;
    private String eventKey;
    private String customerUid;
    private String vendorUid;
    private String timestamp;

    public Message() {
    }

    public Message(String text, String user, String photoUrl, String eventKey, String customerUid, String vendorUid, String timestamp) {
        this.text = text;
        this.user = user;
        this.photoUrl = photoUrl;
        this.eventKey = eventKey;
        this.customerUid = customerUid;
        this.vendorUid = vendorUid;
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

    public String getCustomerUid() {
        return customerUid;
    }

    public void setCustomerUid(String customerUid) {
        this.customerUid = customerUid;
    }

    public String getVendorUid() {
        return vendorUid;
    }

    public void setVendorUid(String vendorUid) {
        this.vendorUid = vendorUid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
