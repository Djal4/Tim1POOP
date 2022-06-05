package com.ldg.main.Models;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "sightseeing")
public class Sightseeing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "user_id")
    //ForeignKey
    private long userId;

    @Column(name = "advertisment_id")
    //ForeignKey
    private long adId;

    @Column(name = "accepted")
    private boolean accepted;

    @Column(name = "time")
    private LocalDateTime time;

    @Column(name = "mark")
    private float mark;
    public Sightseeing(long id, long userId, long adId, boolean accepted, LocalDateTime time, float mark) {
        this.id = id;
        this.userId = userId;
        this.adId = adId;
        this.accepted = accepted;
        this.time = time;
        this.mark = mark;
    }
    public Sightseeing(long userId, long adId, LocalDateTime time) {
        this.userId = userId;
        this.adId = adId;
        this.time = time;
        this.accepted=false;
    }
    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public void setId(long id) {
        this.id = id;
    }
    public long getAdId() {
        return adId;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public float getMark() {
        return mark;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setAdId(long adId) {
        this.adId = adId;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }
}
