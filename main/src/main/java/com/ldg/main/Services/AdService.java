package com.ldg.main.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldg.main.Models.*;
import com.ldg.main.Repository.AdCategoryRepository;
import com.ldg.main.Repository.AdRepository;
import com.ldg.main.Repository.LocationRepository;
import com.ldg.main.payload.request.AdCreateRequest;

import java.util.*;

@Service
public class AdService {
    @Autowired
    private AdRepository adRepository;

    @Autowired
    private AdCategoryRepository adCategoryRepository;

    @Autowired
    private LocationRepository locationRepository;

    public List<Ad> findAll() {
        return adRepository.findAll();
    }

    public void create(AdCreateRequest adCreateRequest) {
        Ad ad = new Ad(adCreateRequest);
        ad.setAdCategory(adCategoryRepository.findById(adCreateRequest.getAdCategoryId()).get());
        // Location location = locationRepository.save(new
        // Location(adCreateRequest.getCity(), adCreateRequest.getStreet(),
        // adCreateRequest.getFlatNumber(), adCreateRequest.getCountry()));
        ad.setLocationId(1);
        adRepository.save(ad);
    }

    public void update(long id, AdCreateRequest adCreateRequest) throws Exception {
        // Ad ad=adRepository.findById(id).get();
        // if(ad==null)
        // throw new Exception("Prazno");
        Ad ad = adRepository.findById(id).get();
        ad.setArea(200);
        adRepository.save(ad);
    }
}
