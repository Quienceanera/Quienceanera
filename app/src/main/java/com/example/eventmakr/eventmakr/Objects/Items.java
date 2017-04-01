package com.example.eventmakr.eventmakr.Objects;

public class Items {

    public String key;
    public String quantity;
    public String price;
    public String instructions;
    public String name;
    public String photo;
    public String vendorName;
    private String vendorKey;
    private String vendorId;

    public Items() {
    }

    public Items(String key, String quantity, String price, String instructions, String name, String photo, String vendorName, String vendorKey, String vendorId) {
        this.key = key;
        this.quantity = quantity;
        this.price = price;
        this.instructions = instructions;
        this.name = name;
        this.photo = photo;
        this.vendorName = vendorName;
        this.vendorKey = vendorKey;
        this.vendorId = vendorId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
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

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorKey() {
        return vendorKey;
    }

    public void setVendorKey(String vendorKey) {
        this.vendorKey = vendorKey;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }
}
