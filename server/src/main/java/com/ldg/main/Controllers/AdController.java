package com.ldg.main.Controllers;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ldg.main.Models.*;
import com.ldg.main.Repository.*;
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
        map.put("message", "Created");
        return ResponseEntity.status(HttpStatus.CREATED).body(map);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @Valid @RequestBody AdCreateRequest ad)
            throws HttpStatusCodeException, Exception {
        Optional<Ad> optionalAdv = adRepository.findById(id);
        if (!optionalAdv.isPresent())
            throw new HttpStatusCodeException(HttpStatus.NOT_FOUND, "Advertisment with id " + id + " not exists!");
        Ad adv = optionalAdv.get();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        if (adv.getOwnerId() != user.getID())
            throw new HttpStatusCodeException(HttpStatus.FORBIDDEN, "You can\'t update ads if you are not owner");
        adService.update(id, adv, ad);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Updated");
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    @DeleteMapping("/{id}") // have permission to delete NOTE: foreign key in sightseeing and likes must be
                            // CASCADE
    public ResponseEntity<?> destroy(@PathVariable("id") long id) throws HttpStatusCodeException {
        Optional<Ad> opt = adRepository.findById(id);
        if (!opt.isPresent())
            throw new HttpStatusCodeException(HttpStatus.NOT_FOUND, "Advertisment with id " + id + " not exists!");
        Ad ad = opt.get();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        if (ad.getOwnerId() != user.getID())
            throw new HttpStatusCodeException(HttpStatus.FORBIDDEN,
                    "You can\'t delete this advertisment because you aren\'t owner");
        adRepository.deleteById(id);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Deleted");
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

}
