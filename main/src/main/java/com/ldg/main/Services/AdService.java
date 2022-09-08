package com.ldg.main.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldg.main.Models.*;
import com.ldg.main.Repository.AdCategoryRepository;
import com.ldg.main.Repository.AdRepository;
import com.ldg.main.payload.request.AdCreateRequest;

import java.util.*;

@Service
public class AdService {
    @Autowired
    private AdRepository adRepository;

    @Autowired
    private AdCategoryRepository adCategoryRepository;

    public List<Ad> findAll() {
        return adRepository.findAll();
    }

    public void create(AdCreateRequest adCreateRequest) {
        Ad ad = new Ad(adCreateRequest);
        ad.setAdCategory(adCategoryRepository.findById(adCreateRequest.getAdCategoryId()).get());
        adRepository.save(ad);
    }

    public void update(long id, AdCreateRequest adCreateRequest) throws Exception {
        // Ad ad=adRepository.findById(id).get();
        // if(ad==null)
        // throw new Exception("Prazno");
        Ad ad = adRepository.findById(id).get();
        adRepository.save(ad);
    }
}
