package com.example.recyclermvvm.Model;

public class NicePlaces {
    private String placeImage;
    private String placeName;

    public NicePlaces(String placeImage, String placeName) {
        this.placeImage = placeImage;
        this.placeName = placeName;
    }

    public String getPlaceImage() {
        return placeImage;
    }

    public String getPlaceName() {
        return placeName;
    }
}
