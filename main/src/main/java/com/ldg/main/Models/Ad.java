package com.ldg.main.Models;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.lang.Nullable;

@Entity
@Table(name = "advertisments")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Min(0)
    @Digits(integer = 10, fraction = 0)
    @Column(name = "advertisment_category_id")
    private long adCategoryId = -1;

    @Min(0)
    @Digits(integer = 10, fraction = 0)
    @Column(name = "owner_id")
    private long ownerId = -1;

    @Min(0)
    @Digits(integer = 10, fraction = 0)
    @Column(name = "location_id")
    private long locationId = -1;

    @Min(0)
    @Digits(integer = 10, fraction = 2)
    @Column(name = "area")
    private float area = -1;

    @Min(0)
    @Digits(integer = 10, fraction = 2)
    @Column(name = "price")
    private float price = -1;

    @Nullable()
    @Column(name = "description")
    private String description = "";

    public Ad() {

    }

    public Ad(long id, long adCategoryId, long ownerId) {
        this.id = id;
        this.adCategoryId = adCategoryId;
        this.ownerId = ownerId;
    }

    public long getId() {
        return id;
    }

    public void setAdCategoryId(long adCategoryId) {
        this.adCategoryId = adCategoryId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public long getAdCategoryId() {
        return adCategoryId;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
