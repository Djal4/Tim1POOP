package com.ldg.main.Models;

import javax.persistence.*;

@Entity
@Table(name = "advertisments")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "advertisment_category_id")
    private int adCategoryId;

    @Column(name = "owner_id")
    private int ownerId;

    @Column(name = "location_id")
    private int locationId;

    @Column(name="area")
    private float area;

    @Column(name = "price")
    private float price;

    @Column(name = "description")
    private String description;

    public Ad(){
        
    }

    public Ad(Integer id, int adCategoryId, int ownerId) {
        this.id = id;
        this.adCategoryId = adCategoryId;
        this.ownerId = ownerId;
    }

    public Integer getId() {
        return id;
    }

    public void setAdCategoryId(int adCategoryId) {
        this.adCategoryId = adCategoryId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getAdCategoryId() {
        return adCategoryId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
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
