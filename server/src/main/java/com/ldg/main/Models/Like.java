package com.ldg.main.Models;

import javax.persistence.*;

@Entity
@Table(name = "likes")
@IdClass(LikeId.class)
public class Like {
    @Id
    @Column(name = "user_id")
    private long userId;

    @Id
    @Column(name = "advertisment_id")
    private long adId;

    public Like() {
    }

    public Like(long adId, long userId) {
        this.adId = adId;
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getAdId() {
        return adId;
    }

    public void setAdId(long adId) {
        this.adId = adId;
    }

}
