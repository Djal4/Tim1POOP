package com.ldg.main.payload.response;

import com.ldg.main.Models.*;

public class AdResponse {

    public long id;
    // public AdCategory adCategory;
    public String adCategory;
    // private long location;
    public float area;
    public float price;
    public String description;
    public UserShortView owner;

    public AdResponse() {
    }

    // public AdResponse(Ad ad, AdCategory adCategory, User owner) {
    public AdResponse(Ad ad, AdCategory adCategory, User owner) {
        this.id = ad.getId();
        this.area = ad.getArea();
        this.price = ad.getPrice();
        this.description = ad.getDescription();
        // this.adCategory = adCategory;
        this.adCategory = adCategory.getTitle();
        this.owner = new UserShortView(owner);
    }
}
