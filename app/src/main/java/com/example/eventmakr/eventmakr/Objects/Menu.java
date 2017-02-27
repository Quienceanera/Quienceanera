package com.example.eventmakr.eventmakr.Objects;

public class Menu {

    public String name;
    public String photo;
    public String price;
    public String details;
    public String key;
    private String vendorUid;

    public Menu() {
    }

    public Menu(String name, String photo, String price, String details, String key, String vendorUid) {
        this.name = name;
        this.photo = photo;
        this.price = price;
        this.details = details;
        this.key = key;
        this.vendorUid = vendorUid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getVendorUid() {
        return vendorUid;
    }

    public void setVendorUid(String vendorUid) {
        this.vendorUid = vendorUid;
    }
}
