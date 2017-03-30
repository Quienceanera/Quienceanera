package com.example.eventmakr.eventmakr.Objects;

public class Feedback {
    private String uid;
    private String name;
    private String feedback;

    public Feedback() {
    }

    public Feedback(String uid, String name, String feedback) {
        this.uid = uid;
        this.name = name;
        this.feedback = feedback;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
