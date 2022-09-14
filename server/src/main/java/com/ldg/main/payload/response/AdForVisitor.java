package com.ldg.main.payload.response;

import com.ldg.main.Models.*;

public class AdForVisitor {
    public UserShortView owner;
    public long id;
    // public AdCategory adCategory;
    public String adCategory;
    // private long city;
    public float area;
    public float price;
    public String description;
    public String city;
    public String country;
    public long numberOfLikes;

    public AdForVisitor(Ad ad, AdCategory adCategory, User owner, City city, Country country, long numberOfLikes) {
        this.id = ad.getId();
        this.area = ad.getArea();
        this.price = ad.getPrice();
        this.description = ad.getDescription();
        // this.adCategory = adCategory;
        this.adCategory = adCategory.getTitle();
        this.city = city.getName();
        this.country = country.getName();
        this.owner = new UserShortView(owner);
        this.numberOfLikes = numberOfLikes;
    }
}
