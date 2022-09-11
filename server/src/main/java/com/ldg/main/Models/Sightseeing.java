package com.ldg.main.Models;

import javax.persistence.*;

@Entity
@Table(name = "sightseeing")
public class Sightseeing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "user_id")
    // ForeignKey
    private long userId;

    @Column(name = "advertisment_id")
    // ForeignKey
    private long adId;

    @Column(name = "accepted")
    private Boolean accepted;

    @Column(name = "time")
    private String time;

    @Column(name = "mark")
    private int mark;

    @Column(name = "comment")
    private String comment;

    public Sightseeing() {

    }

    public Sightseeing(long id, long userId, long adId, Boolean accepted, String time, int mark) {
        this.id = id;
        this.userId = userId;
        this.adId = adId;
        this.accepted = accepted;
        this.time = time;
        this.mark = mark;
    }

    public Sightseeing(long userId, long adId, String time) {
        this.userId = userId;
        this.adId = adId;
        this.time = time;
        this.accepted = null;
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

    public Boolean getAccepted() {
        return accepted;
    }

    public String getTime() {
        return time;
    }

    public int getMark() {
        return mark;
    }

    public String getComment() {
        return comment;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setAdId(long adId) {
        this.adId = adId;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = Boolean.valueOf(accepted);
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
