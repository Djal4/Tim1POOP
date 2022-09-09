package com.ldg.main.payload.response;

import com.ldg.main.Models.*;

public class AdResponse {

    public long id;
    public AdCategory adCategory;
    // private long location;
    public float area;
    public float price;
    public String description;
    public UserResponseForAd owner;

    public AdResponse() {
    }

    public AdResponse(Ad ad, AdCategory adCategory, User owner) {
        this.id = ad.getId();
        this.area = ad.getArea();
        this.price = ad.getPrice();
        this.description = ad.getDescription();
        this.adCategory = adCategory;
        this.owner = new UserResponseForAd(owner);
    }
}
