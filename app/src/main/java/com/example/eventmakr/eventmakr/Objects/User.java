package com.example.eventmakr.eventmakr.Objects;

public class User {
    private String name;
    private String uid;
    private String email;
    private String photo;
    private String updates;
    private String updates2;

    public User() {
    }

    public User(String name, String uid, String email, String photo, String updates, String updates2) {
        this.name = name;
        this.uid = uid;
        this.email = email;
        this.photo = photo;
        this.updates = updates;
        this.updates2 = updates2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUpdates() {
        return updates;
    }

    public void setUpdates(String updates) {
        this.updates = updates;
    }

    public String getUpdates2() {
        return updates2;
    }

    public void setUpdates2(String updates2) {
        this.updates2 = updates2;
    }
}
