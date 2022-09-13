package com.ldg.main.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ldg.main.Repository.*;
import com.ldg.main.Models.*;
import com.ldg.main.payload.response.*;

import java.util.*;

@RestController
@RequestMapping("/api/country")
public class CountryController {
    @Autowired
    CountryRepository countryRepository;

    @Autowired
    CityRepository cityRepository;

    @GetMapping
    public List<?> getAll() {
        List<CountryResponse> response = new ArrayList<>();
        List<Country> countries = countryRepository.findAll();
        for (Country country : countries) {
            CountryResponse countr = new CountryResponse(country);
            List<City> cities = cityRepository.findByCountryId(countr.id);
            for (City city : cities) {
                countr.cities.add(new CityResponse(city));
            }
            response.add(countr);
        }
        return response;
    }
}
