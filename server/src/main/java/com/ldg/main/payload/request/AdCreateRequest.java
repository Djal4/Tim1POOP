package com.ldg.main.payload.request;

import javax.validation.constraints.Min;

import org.springframework.lang.Nullable;

public class AdCreateRequest {
    public long id;

    @Min(1)
    private long adCategoryId;

    private long ownerId;

    @Min(1)
    private float area;

    @Min(1)
    private float price;

    @Nullable
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
}
