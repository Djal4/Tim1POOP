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

    @Autowired
    RoleRepository roleRepository;

    // Public route
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(adService.createAdForVisitorResponseList());
    }

    // Public route
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") long id) throws HttpStatusCodeException {
        Optional<Ad> optionalAd = adRepository.findById(id);
        if (optionalAd.isPresent()) {
            Ad ad = optionalAd.get();
            return ResponseEntity.ok(adService.getDetailed(ad));
        }
        throw new HttpStatusCodeException(HttpStatus.NOT_FOUND, "Advertisment with id " + id + " not exists!");
    }

    @GetMapping("/detailed/{id}")
    public ResponseEntity<?> getDetailedForLoggedInUser(@PathVariable("id") long id) throws HttpStatusCodeException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        Optional<Ad> optionalAd = adRepository.findById(id);
        if (optionalAd.isPresent()) {
            Ad ad = optionalAd.get();
            return ResponseEntity.ok(adService.getDetailedLoggedIn(ad, user.getID()));
        }
        throw new HttpStatusCodeException(HttpStatus.NOT_FOUND, "Advertisment with id " + id + " not exists!");
    }

    @GetMapping("/my") // get from token
    public ResponseEntity<?> myAds() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        return ResponseEntity.status(HttpStatus.OK)
                .body(adService.createAdMyResponseList(adService.findMyAds(user.getID()), user.getID()));
    }

    @GetMapping("/other")
    public ResponseEntity<?> otherAds() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        return ResponseEntity.status(HttpStatus.OK)
                .body(adService.createAdResponseList(adService.findOtherAds(user.getID()), user.getID()));
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
        Role ADMIN_ROLE = roleRepository.findByTitle("ADMIN");
        long userRoleId = userRepository.findById(user.getID()).get().getRoleID();
        if (ad.getOwnerId() != user.getID() || userRoleId != ADMIN_ROLE.getId())
            throw new HttpStatusCodeException(HttpStatus.FORBIDDEN,
                    "You can\'t delete this advertisment because you aren\'t owner");
        adRepository.deleteById(id);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Deleted");
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

}
