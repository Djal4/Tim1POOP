package com.ldg.main.payload.response;

public class SightseeingUserResponse {
    public long id;
    public AdShortView ad;
    public Boolean accepted;
    public String time;
    public int mark;
    public String comment;

    public SightseeingUserResponse(long id, AdShortView ad, Boolean accepted, String time,
            int mark, String comment) {
        this.id = id;
        this.ad = ad;
        this.accepted = accepted;
        this.time = time;
        this.mark = mark;
        this.comment = comment;
    }
}
