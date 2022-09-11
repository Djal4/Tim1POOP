package com.ldg.main.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldg.main.Models.*;
import com.ldg.main.Repository.AdRepository;
import com.ldg.main.payload.request.AdCreateRequest;

import java.util.*;

@Service
public class AdService {
    @Autowired
    private AdRepository adRepository;

    public List<Ad> findAll() {
        return adRepository.findAll();
    }

    public void create(AdCreateRequest adCreateRequest) {
        Ad ad = new Ad(adCreateRequest);
        adRepository.save(ad);
    }

    public void update(long id, Ad ad, AdCreateRequest adCreateRequest) throws Exception {
        ad.setAdCategoryId(adCreateRequest.getAdCategoryId());
        ad.setArea(adCreateRequest.getArea());
        ad.setPrice(adCreateRequest.getPrice());
        ad.setDescription(adCreateRequest.getDescription());
        adRepository.save(ad);
    }
}
