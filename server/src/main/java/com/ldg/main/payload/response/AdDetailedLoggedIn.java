package com.ldg.main.payload.response;

import com.ldg.main.Models.Ad;
import com.ldg.main.Models.AdCategory;
import com.ldg.main.Models.City;
import com.ldg.main.Models.Country;
import com.ldg.main.Models.User;

public class AdDetailedLoggedIn extends AdDetailed {
    public boolean myAd;
    public boolean liked;

    public AdDetailedLoggedIn(Ad ad, AdCategory adCategory, User owner, City city, Country country,
            long numberOfLikes) {
        super(ad, adCategory, owner, city, country, numberOfLikes);
    }

}
