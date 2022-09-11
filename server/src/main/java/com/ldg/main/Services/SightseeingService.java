package com.ldg.main.Services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldg.main.Models.*;
import com.ldg.main.Repository.*;
import com.ldg.main.payload.response.*;

@Service
public class SightseeingService {

    @Autowired
    SightseeingRepository sightseeingRepository;

    @Autowired
    AdRepository adRepository;

    @Autowired
    AdCategoryRepository adCategoryRepository;

    @Autowired
    UserRepository userRepository;

    public List<?> getOwnerSightseeings(long id) {
        List<Sightseeing> sightseeings = sightseeingRepository.findByOwnerId(id);
        List<SightseeingOwnerResponse> response = new ArrayList<>();
        for (Sightseeing s : sightseeings) {
            Ad ad = adRepository.getReferenceById(s.getAdId());
            AdCategory adCategory = adCategoryRepository.getReferenceById(ad.getAdCategoryId());
            User user = userRepository.getReferenceById(s.getUserId());
            SightseeingOwnerResponse re = new SightseeingOwnerResponse(s.getId(), new UserShortView(user),
                    new AdShortView(ad, adCategory), s.getAccepted(), s.getTime(), s.getMark(), s.getComment());
            response.add(re);
        }
        return response;
    }

    public List<?> getUserSightseeings(long id) {
        List<Sightseeing> sightseeings = sightseeingRepository.findByUserId(id);
        List<SightseeingUserResponse> response = new ArrayList<>();
        for (Sightseeing s : sightseeings) {
            Ad ad = adRepository.getReferenceById(s.getAdId());
            AdCategory adCategory = adCategoryRepository.getReferenceById(ad.getAdCategoryId());
            SightseeingUserResponse re = new SightseeingUserResponse(s.getId(),
                    new AdShortView(ad, adCategory), s.getAccepted(), s.getTime(), s.getMark(), s.getComment());
            response.add(re);
        }
        return response;
    }
}
