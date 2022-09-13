package com.ldg.main.payload.response;

import java.util.*;

import com.ldg.main.Models.Country;

public class CountryResponse {
    public long id;
    public String name;
    public List<CityResponse> cities;

    public CountryResponse(Country c) {
        this.id = c.getId();
        this.name = c.getName();
        this.cities = new ArrayList<>();
    }
}
