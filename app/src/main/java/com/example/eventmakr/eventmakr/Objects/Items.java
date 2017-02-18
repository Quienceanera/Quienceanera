package com.example.eventmakr.eventmakr.Objects;

public class Items {

    public String key;
    public String quantity;
    public String price;
    public String name;
    public String photo;

    public Items() {
    }

    public Items(String key, String quantity, String price, String name, String photo) {
        this.key = key;
        this.quantity = quantity;
        this.price = price;
        this.name = name;
        this.photo = photo;
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
}
