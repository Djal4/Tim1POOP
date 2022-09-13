package com.ldg.main.payload.response;

import com.ldg.main.Models.*;

public class AdResponse extends AdMyResponse {

    public UserShortView owner;

    public AdResponse() {
    }

    // public AdResponse(Ad ad, AdCategory adCategory, User owner) {
    public AdResponse(Ad ad, AdCategory adCategory, User owner, City city, Country country) {
        super(ad, adCategory, city, country);
        this.owner = new UserShortView(owner);
    }
}
