package com.example.eventmakr.eventmakr.Objects;

public class Cart {
    private String eventDate;
    private String eventType;
    private String eventAddress;
    private String eventName;
    private String eventKey;
    private String consumerUid;
    private String vendorUid;
    private String priceTotal;
    private String itemCount;
    private String vendorName;
    private String vendorLogo;
    private String timeStamp;
    private String confirm;
    private String paid;

    public Cart() {
    }

    public Cart(String eventDate, String eventType, String eventAddress, String eventName, String eventKey, String consumerUid, String vendorUid, String priceTotal, String itemCount, String vendorName, String vendorLogo, String timeStamp, String confirm, String paid) {
        this.eventDate = eventDate;
        this.eventType = eventType;
        this.eventAddress = eventAddress;
        this.eventName = eventName;
        this.eventKey = eventKey;
        this.consumerUid = consumerUid;
        this.vendorUid = vendorUid;
        this.priceTotal = priceTotal;
        this.itemCount = itemCount;
        this.vendorName = vendorName;
        this.vendorLogo = vendorLogo;
        this.timeStamp = timeStamp;
        this.confirm = confirm;
        this.paid = paid;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventAddress() {
        return eventAddress;
    }

    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getConsumerUid() {
        return consumerUid;
    }

    public void setConsumerUid(String consumerUid) {
        this.consumerUid = consumerUid;
    }

    public String getVendorUid() {
        return vendorUid;
    }

    public void setVendorUid(String vendorUid) {
        this.vendorUid = vendorUid;
    }

    public String getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(String priceTotal) {
        this.priceTotal = priceTotal;
    }

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorLogo() {
        return vendorLogo;
    }

    public void setVendorLogo(String vendorLogo) {
        this.vendorLogo = vendorLogo;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }
}
