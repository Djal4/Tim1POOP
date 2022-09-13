package com.ldg.main.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldg.main.Models.*;
import com.ldg.main.Repository.*;
import com.ldg.main.payload.request.*;
import com.ldg.main.payload.response.*;

import java.util.*;

@Service
public class AdService {
    @Autowired
    private AdRepository adRepository;

    @Autowired
    AdCategoryRepository adCategoryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    CountryRepository countryRepository;

    public List<Ad> findAll() {
        return adRepository.findAll();
    }

    public List<Ad> findMyAds(long id) {
        return adRepository.findMyAds(id);
    }

    public List<Ad> findOtherAds(long id) {
        return adRepository.findOtherAds(id);
    }

    /*
     * public SingleAdResponse createSingleAdResponse(Ad ad){
     * AdCategory adCategory =
     * adCategoryRepository.findById(ad.getAdCategoryId()).get();
     * City city=cityRepository.findById(ad.getCityId()).get();
     * Country country=countryRepository.findById(city.getCountryId()).get();
     * }
     */
    public AdResponse createAdResponse(Ad ad) {
        AdCategory adCategory = adCategoryRepository.findById(ad.getAdCategoryId()).get();
        User owner = userRepository.findById(ad.getOwnerId()).get();
        City city = cityRepository.findById(ad.getCityId()).get();
        Country country = countryRepository.findById(city.getCountryId()).get();
        return new AdResponse(ad, adCategory, owner, city, country);
    }

    public AdMyResponse createAdMyResponse(Ad ad) {
        AdCategory adCategory = adCategoryRepository.findById(ad.getAdCategoryId()).get();
        City city = cityRepository.findById(ad.getCityId()).get();
        Country country = countryRepository.findById(city.getCountryId()).get();
        return new AdMyResponse(ad, adCategory, city, country);
    }

    public List<AdResponse> createAdResponseList(List<Ad> ads) {
        List<AdResponse> adResponses = new ArrayList<>();
        for (Ad ad : ads) {
            adResponses.add(createAdResponse(ad));
        }
        return adResponses;
    }

    public List<AdMyResponse> createAdMyResponseList(List<Ad> ads) {
        List<AdMyResponse> adResponses = new ArrayList<>();
        for (Ad ad : ads) {
            adResponses.add(createAdMyResponse(ad));
        }
        return adResponses;
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
