package com.ldg.main.payload.request;

import javax.validation.constraints.Min;

import org.springframework.lang.Nullable;

public class AdCreateRequest {
    public long id;

    @Min(0)
    private long adCategoryId = -1;

    @Min(0)
    private long ownerId = -1;

    @Min(0)
    private float area = -1;

    @Min(0)
    private float price = -1;

    private String description = "";

    public long getAdCategoryId() {
        return adCategoryId;
    }

    public float getArea() {
        return area;
    }

    public String getDescription() {
        return description;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public float getPrice() {
        return price;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    // Location data
    @Nullable
    private long locationId = 1;

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    @Nullable
    private String city;

    @Nullable
    private String street;

    @Nullable
    private String flatNumber;

    @Nullable
    private String country;

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public String getStreet() {
        return street;
    }
}
