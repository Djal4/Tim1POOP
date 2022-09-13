package com.ldg.main.payload.response;

import com.ldg.main.Models.City;

public class CityResponse {
    public long id;
    public String name;

    public CityResponse(City city) {
        this.id = city.getId();
        this.name = city.getName();
    }
}
