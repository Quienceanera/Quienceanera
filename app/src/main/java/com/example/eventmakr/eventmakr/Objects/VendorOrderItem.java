package com.example.eventmakr.eventmakr.Objects;

public class VendorOrderItem {

    public String itemKey;
    public String quantity;
    public String price;
    public String totalPrice;
    public String name;
    public String photo;

    public VendorOrderItem() {
    }

    public VendorOrderItem(String itemKey, String quantity, String price, String totalPrice, String name, String photo) {
        this.itemKey = itemKey;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
        this.name = name;
        this.photo = photo;
    }

    public String getItemKey() {
        return itemKey;
    }

    public void setItemKey(String itemKey) {
        this.itemKey = itemKey;
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

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
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
