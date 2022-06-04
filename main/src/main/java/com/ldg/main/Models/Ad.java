package com.ldg.main.Models;

import javax.persistence.*;

@Entity
@Table(name = "advertisments")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "flat_id")
    private int flatId;

    @Column(name = "advertisment_category_id")
    private int adCategoryId;

    @Column(name = "owner_id")
    private int ownerId;

    public Ad(){
        
    }

    public Ad(Integer id, int flatId, int adCategoryId, int ownerId) {
        this.id = id;
        this.flatId = flatId;
        this.adCategoryId = adCategoryId;
        this.ownerId = ownerId;
    }

    public Integer getId() {
        return id;
    }

    public void setFlatId(int flatId) {
        this.flatId = flatId;
    }

    public void setAdCategoryId(int adCategoryId) {
        this.adCategoryId = adCategoryId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getFlatId() {
        return flatId;
    }

    public int getAdCategoryId() {
        return adCategoryId;
    }

    public int getOwnerId() {
        return ownerId;
    }
}
