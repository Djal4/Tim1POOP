package com.ldg.main.Models;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "sightseeing")
public class Sightseeing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    //ForeignKey
    private int userId;

    @Column(name = "advertisment_id")
    //ForeignKey
    private int adId;

    @Column(name = "accepted")
    private boolean accepted;

    @Column(name = "accepted")
    private LocalDateTime time;

    @Column(name = "mark")
    private float mark;
    public Sightseeing(Integer id, int userId, int adId, boolean accepted, LocalDateTime time, float mark) {
        this.id = id;
        this.userId = userId;
        this.adId = adId;
        this.accepted = accepted;
        this.time = time;
        this.mark = mark;
    }
    public Integer getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getAdId() {
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

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setAdId(int adId) {
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
