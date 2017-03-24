package com.example.eventmakr.eventmakr.Objects;

public class ChatHome {
    private String vendorName;
    private String vendorPhoto;
    private String vendorUid;
    private String consumerName;
    private String consumerPhoto;
    private String consumerUid;
    private String timestamp;
    private String eventKey;
    private String eventName;
    private String eventDate;

    public ChatHome() {
    }

    public ChatHome(String vendorName, String vendorPhoto, String vendorUid, String consumerName, String consumerPhoto, String consumerUid, String timestamp, String eventKey, String eventName, String eventDate) {
        this.vendorName = vendorName;
        this.vendorPhoto = vendorPhoto;
        this.vendorUid = vendorUid;
        this.consumerName = consumerName;
        this.consumerPhoto = consumerPhoto;
        this.consumerUid = consumerUid;
        this.timestamp = timestamp;
        this.eventKey = eventKey;
        this.eventName = eventName;
        this.eventDate = eventDate;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorPhoto() {
        return vendorPhoto;
    }

    public void setVendorPhoto(String vendorPhoto) {
        this.vendorPhoto = vendorPhoto;
    }

    public String getVendorUid() {
        return vendorUid;
    }

    public void setVendorUid(String vendorUid) {
        this.vendorUid = vendorUid;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public String getConsumerPhoto() {
        return consumerPhoto;
    }

    public void setConsumerPhoto(String consumerPhoto) {
        this.consumerPhoto = consumerPhoto;
    }

    public String getConsumerUid() {
        return consumerUid;
    }

    public void setConsumerUid(String consumerUid) {
        this.consumerUid = consumerUid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }
}