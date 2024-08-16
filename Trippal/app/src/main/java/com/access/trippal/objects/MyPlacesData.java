package com.access.trippal.objects;

public class MyPlacesData {

    private String placeName;
    private String placeDate;
    private Integer placeImage;

    public MyPlacesData(String placeName, String placeDate, Integer placeImage) {
        this.placeName = placeName;
        this.placeDate = placeDate;
        this.placeImage = placeImage;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceDate() {
        return placeDate;
    }

    public void setPlaceDate(String placeDate) {
        this.placeDate = placeDate;
    }

    public Integer getPlaceImage() {
        return placeImage;
    }

    public void setPlaceImage(Integer placeImage) {
        this.placeImage = placeImage;
    }

}