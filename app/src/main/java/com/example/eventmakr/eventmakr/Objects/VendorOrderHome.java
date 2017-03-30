package com.example.eventmakr.eventmakr.Objects;

public class VendorOrderHome {
    private String customerName;
    private String customerPhoto;
    private String customerUid;
    private String totalPrice;
    private String totalQuantity;
    private String timestamp;
    private String eventKey;
    private String eventName;
    private String eventDate;
    private String zipCode;

    public VendorOrderHome() {
    }

    public VendorOrderHome(String customerName, String customerPhoto, String customerUid, String totalPrice, String totalQuantity, String timestamp, String eventKey, String eventName, String eventDate, String zipCode) {
        this.customerName = customerName;
        this.customerPhoto = customerPhoto;
        this.customerUid = customerUid;
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
        this.timestamp = timestamp;
        this.eventKey = eventKey;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.zipCode = zipCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhoto() {
        return customerPhoto;
    }

    public void setCustomerPhoto(String customerPhoto) {
        this.customerPhoto = customerPhoto;
    }

    public String getCustomerUid() {
        return customerUid;
    }

    public void setCustomerUid(String customerUid) {
        this.customerUid = customerUid;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}