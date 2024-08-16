package com.access.trippal.objects;

public class User {
    private String uId;
    private  String firstName;
    private  String lastName;
    private  String pictureUrl;

    public User() {
    }

    public User(String uId, String firstName, String lastName, String pictureUrl) {
        this.uId = uId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pictureUrl = pictureUrl;
    }

    public String getuId() {
        return uId;
    }

    public User setuId(String uId) {
        this.uId = uId;
        return this;
    }
    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public User setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }

}