package com.example.eventmakr.eventmakr.Objects;

public class VendorUser {
    private String name;
    private String owner;
    private String contact;
    private String vendorUid;
    private String vendorKey;
    private String address;
    private String zipcode;
    private String description;
    private String price;
    private String logo;
    private String category;
    private String vendorStatus;

    public VendorUser() {
    }

    public VendorUser(String name, String owner, String contact, String vendorUid, String vendorKey, String address, String zipcode, String description, String price, String logo, String category, String vendorStatus) {
        this.name = name;
        this.owner = owner;
        this.contact = contact;
        this.vendorUid = vendorUid;
        this.vendorKey = vendorKey;
        this.address = address;
        this.zipcode = zipcode;
        this.description = description;
        this.price = price;
        this.logo = logo;
        this.category = category;
        this.vendorStatus = vendorStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getVendorUid() {
        return vendorUid;
    }

    public void setVendorUid(String vendorUid) {
        this.vendorUid = vendorUid;
    }

    public String getVendorKey() {
        return vendorKey;
    }

    public void setVendorKey(String vendorKey) {
        this.vendorKey = vendorKey;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getVendorStatus() {
        return vendorStatus;
    }

    public void setVendorStatus(String vendorStatus) {
        this.vendorStatus = vendorStatus;
    }
}
