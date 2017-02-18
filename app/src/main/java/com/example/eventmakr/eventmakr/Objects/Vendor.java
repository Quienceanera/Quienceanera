package com.example.eventmakr.eventmakr.Objects;

public class Vendor {

    private String name;
    private String owner;
    private String contact;
    private String id;
    private String address;
    private String zipcode;
    private String description;
    private String price;
    private String logo;
    private String category;

    public Vendor() {
    }

    public Vendor(String name, String owner, String contact, String id, String address, String zipcode, String description, String price, String logo, String category) {
        this.name = name;
        this.owner = owner;
        this.contact = contact;
        this.id = id;
        this.address = address;
        this.zipcode = zipcode;
        this.description = description;
        this.price = price;
        this.logo = logo;
        this.category = category;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
