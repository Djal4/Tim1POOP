package com.ldg.main.payload.response;

public class SightseeingOwnerResponse {
    public long id;
    public UserShortView user;
    public AdShortView ad;
    public Boolean accepted;
    public String time;
    public int mark;
    public String comment;

    public SightseeingOwnerResponse(long id, UserShortView user, AdShortView ad, Boolean accepted, String time,
            int mark, String comment) {
        this.id = id;
        this.user = user;
        this.ad = ad;
        this.accepted = accepted;
        this.time = time;
        this.mark = mark;
        this.comment = comment;
    }

}
