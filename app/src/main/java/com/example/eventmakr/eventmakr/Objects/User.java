package com.example.eventmakr.eventmakr.Objects;

public class User {
private String name;
    private String uid;
    private String email;
    private String address;
    private boolean updates;
    private boolean updates2;

    public User() {
    }

    public User(String name, String uid, String email, String address, boolean updates, boolean updates2) {
        this.name = name;
        this.uid = uid;
        this.email = email;
        this.address = address;
        this.updates = updates;
        this.updates2 = updates2;
    }
}
