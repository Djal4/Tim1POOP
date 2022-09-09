package com.ldg.main.Controllers;

import org.springframework.security.access.AccessDeniedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ldg.main.Models.Ad;
import com.ldg.main.Models.AdCategory;
import com.ldg.main.Models.User;
import com.ldg.main.Models.UserDetailsImpl;
import com.ldg.main.Repository.AdCategoryRepository;
import com.ldg.main.Repository.AdRepository;
import com.ldg.main.Repository.UserRepository;
import com.ldg.main.Services.AdService;
import com.ldg.main.exceptions.HttpStatusCodeException;
import com.ldg.main.payload.request.AdCreateRequest;
import com.ldg.main.payload.response.AdResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/api/ads")
public class AdController {
    @Autowired
    AdService adService;

    @Autowired
    AdRepository adRepository;

    @Autowired
    AdCategoryRepository adCategoryRepository;

    @Autowired
    UserRepository userRepository;

    private AdResponse createAdResponse(Ad ad) {
        AdCategory adCategory = adCategoryRepository.findById(ad.getAdCategoryId()).get();
        User owner = userRepository.findById(ad.getOwnerId()).get();
        return new AdResponse(ad, adCategory, owner);
    }

    // Public route
    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Ad> ads = adService.findAll();
        List<AdResponse> adResponses = new ArrayList<>();
        for (Ad ad : ads) {
            adResponses.add(createAdResponse(ad));
        }
        return ResponseEntity.ok(adResponses);
    }

    // Public route
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") long id) throws HttpStatusCodeException {
        Optional<Ad> optionalAd = adRepository.findById(id);
        if (optionalAd.isPresent()) {
            Ad ad = optionalAd.get();
            return ResponseEntity.ok(createAdResponse(ad));
        }
        throw new HttpStatusCodeException(HttpStatus.NOT_FOUND, "Advertisment with id " + id + " not exists!");
    }

    @GetMapping("/my") // get from token
    public ResponseEntity<?> myAds() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        List<Ad> ads = adRepository.findMyAds(user.getID());
        List<AdResponse> adResponses = new ArrayList<>();
        for (Ad ad : ads) {
            adResponses.add(createAdResponse(ad));
        }
        return ResponseEntity.status(HttpStatus.OK).body(adResponses);
    }

    @GetMapping("/other")
    public ResponseEntity<?> otherAds() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        List<Ad> ads = adRepository.findOtherAds(user.getID());
        List<AdResponse> adResponses = new ArrayList<>();
        for (Ad ad : ads) {
            adResponses.add(createAdResponse(ad));
        }
        return ResponseEntity.status(HttpStatus.OK).body(adResponses);
    }

    @PostMapping
    public ResponseEntity<?> store(@Valid @RequestBody AdCreateRequest ad) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        ad.setOwnerId(user.getID());
        adService.create(ad);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Stored");
        return ResponseEntity.status(HttpStatus.CREATED).body(map);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @Valid @RequestBody AdCreateRequest ad)
            throws Exception {
        Ad adv = adRepository.getReferenceById(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        if (adv.getOwnerId() != user.getID())
            throw new AccessDeniedException("You can\'t update ads if you are not owner");
        adService.update(id, ad);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Updated");
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    @DeleteMapping("/{id}") // have permission to delete
    public ResponseEntity<?> destroy(@PathVariable("id") long id) throws AccessDeniedException {
        Ad ad = adRepository.getReferenceById(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        if (ad.getOwnerId() != user.getID())
            throw new AccessDeniedException("You can\'t delete ads if you are not owner");
        adRepository.deleteById(id);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Deleted");
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

}
