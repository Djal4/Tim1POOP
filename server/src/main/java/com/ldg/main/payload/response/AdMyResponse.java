package com.ldg.main.payload.response;

import com.ldg.main.Models.*;

public class AdMyResponse {

    public long id;
    // public AdCategory adCategory;
    public String adCategory;
    // private long city;
    public float area;
    public float price;
    public String description;
    public String city;
    public String country;
    public boolean liked;
    public long numberOfLikes;
    public float averageMark;

    public AdMyResponse() {
    }

    // public AdResponse(Ad ad, AdCategory adCategory, User owner) {
    public AdMyResponse(Ad ad, AdCategory adCategory, City city, Country country, boolean liked, long numberOfLikes) {
        this.id = ad.getId();
        this.area = ad.getArea();
        this.price = ad.getPrice();
        this.description = ad.getDescription();
        // this.adCategory = adCategory;
        this.adCategory = adCategory.getTitle();
        this.city = city.getName();
        this.country = country.getName();
        this.liked = liked;
        this.numberOfLikes = numberOfLikes;
    }
}
