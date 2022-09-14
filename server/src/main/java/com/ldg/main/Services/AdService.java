package com.ldg.main.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldg.main.Models.*;
import com.ldg.main.Repository.*;
import com.ldg.main.payload.request.*;
import com.ldg.main.payload.response.*;

import java.math.BigInteger;
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

    @Autowired
    LikeRepository likeRepository;

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
    public AdResponse createAdResponse(Ad ad, long userId) {
        AdCategory adCategory = adCategoryRepository.findById(ad.getAdCategoryId()).get();
        User owner = userRepository.findById(ad.getOwnerId()).get();
        City city = cityRepository.findById(ad.getCityId()).get();
        Country country = countryRepository.findById(city.getCountryId()).get();
        Map<String, BigInteger> numberOfLikes = likeRepository.numberOfLikes(ad.getId());
        Map<String, BigInteger> liked = likeRepository.userLikedAd(ad.getId(), userId);
        return new AdResponse(ad, adCategory, owner, city, country,
                liked.get("liked").intValue() == 1,
                numberOfLikes.get("numberOfLikes").intValue());
    }

    public AdMyResponse createAdMyResponse(Ad ad, long userId) {
        AdCategory adCategory = adCategoryRepository.findById(ad.getAdCategoryId()).get();
        City city = cityRepository.findById(ad.getCityId()).get();
        Country country = countryRepository.findById(city.getCountryId()).get();
        Map<String, BigInteger> numberOfLikes = likeRepository.numberOfLikes(ad.getId());
        Map<String, BigInteger> liked = likeRepository.userLikedAd(ad.getId(), userId);
        return new AdMyResponse(ad, adCategory, city, country,
                liked.get("liked").intValue() == 1,
                numberOfLikes.get("numberOfLikes").intValue());
    }

    public List<AdResponse> createAdResponseList(List<Ad> ads, long userId) {
        List<AdResponse> adResponses = new ArrayList<>();
        for (Ad ad : ads) {
            adResponses.add(createAdResponse(ad, userId));
        }
        return adResponses;
    }

    public List<AdMyResponse> createAdMyResponseList(List<Ad> ads, long userId) {
        List<AdMyResponse> adResponses = new ArrayList<>();
        for (Ad ad : ads) {
            adResponses.add(createAdMyResponse(ad, userId));
        }
        return adResponses;
    }

    public AdForVisitor createAdForVisitor(Ad ad) {
        try {
            AdCategory adCategory = adCategoryRepository.findById(ad.getAdCategoryId()).get();
            User owner = userRepository.findById(ad.getOwnerId()).get();
            City city = cityRepository.findById(ad.getCityId()).get();
            Country country = countryRepository.findById(city.getCountryId()).get();
            Map<String, BigInteger> numberOfLikes = likeRepository.numberOfLikes(ad.getId());
            return new AdForVisitor(ad, adCategory, owner, city, country,
                    numberOfLikes.get("numberOfLikes").intValue()); // numberOfLikes.get("numberOfLikes").longValue());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<AdForVisitor> createAdForVisitorResponseList() {
        List<Ad> ads = this.findAll();
        List<AdForVisitor> adResponses = new ArrayList<>();
        for (Ad ad : ads) {
            adResponses.add(createAdForVisitor(ad));
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
