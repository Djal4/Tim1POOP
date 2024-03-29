package com.ldg.main.payload.response;

import com.ldg.main.Models.*;

public class AdShortView {
    public long id;
    // public AdCategory adCategory;
    public String adCategory;
    // private long city;
    public float area;
    public float price;
    public String description;
    public float averageMark;

    public AdShortView(Ad ad, AdCategory adCategory) {
        this.id = ad.getId();
        this.area = ad.getArea();
        this.price = ad.getPrice();
        this.description = ad.getDescription();
        // this.adCategory = adCategory;
        this.adCategory = adCategory.getTitle();
    }
}
