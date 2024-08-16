package com.access.trippal.objects;

import com.access.trippal.enums.Area;
import com.access.trippal.enums.Type;

public class Place {
    private String pid="";
    private String pictureUrl="";
    private Area area=null;
    private String address="";
    private float stars=0f;
    private  String name="";
    private String description="";
    private double cost=0.0;
    private Type type=null;
    private double latitude=0.0;
    private double longitude=0.0;
    private int numberOfComments=0;

    public Place(){

    }

    public Place(String pid, String pictureUrl, Area area, String address, float stars, String name, String description, double cost, Type type, double latitude, double longitude, int numberOfComments) {
        this.pid = pid;
        this.pictureUrl = pictureUrl;
        this.area = area;
        this.address = address;
        this.stars = stars;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.numberOfComments = numberOfComments;
    }

    public int getNumberOfComments() {
        return numberOfComments;
    }

    public Place setNumberOfComments(int numberOfComments) {
        this.numberOfComments = numberOfComments;
        return this;
    }

    public String getPid() {
        return pid;
    }

    public Place setPid(String pid) {
        this.pid = pid;
        return this;
    }

    public double getLatitude() {
        return latitude;
    }

    public Place setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public double getLongitude() {
        return longitude;
    }

    public Place setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public Place setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }

    public Area getArea() {
        return area;
    }

    public Place setArea(Area area) {
        this.area = area;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Place setAddress(String address) {
        this.address = address;
        return this;
    }

    public Type getType() {
        return type;
    }

    public Place setType(Type type) {
        this.type = type;
        return this;
    }


    public float getStars() {
        return stars;
    }

    public Place setStars(float stars) {
        this.stars = stars;
        return this;
    }

    public String getName() {
        return name;
    }

    public Place setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Place setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getCost() {
        return cost;
    }

    public Place setCost(double cost) {
        this.cost = cost;
        return this;
    }
}