package com.access.trippal.objects;

public class Comment {
    private String cid="";
    private String userName="";
    private long date=0L;
    private String comment="";
    private float stars=0f;

    public Comment() {
    }

    public Comment(String cid, String userName, long date, String comment, float stars) {
        this.cid = cid;
        this.userName = userName;
        this.date = date;
        this.comment = comment;
        this.stars = stars;
    }

    public String getCid() {
        return cid;
    }

    public Comment setCid(String cid) {
        this.cid = cid;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public Comment setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public long getDate() {
        return date;
    }

    public Comment setDate(long date) {
        this.date = date;
        return this;
    }

    public float getStars() {
        return stars;
    }

    public Comment setStars(float stars) {
        this.stars = stars;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public Comment setComment(String comment) {
        this.comment = comment;
        return this;
    }
}