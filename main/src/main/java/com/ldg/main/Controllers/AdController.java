package com.ldg.main.Controllers;

import org.springframework.security.access.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ldg.main.Models.Ad;
import com.ldg.main.Models.UserDetailsImpl;
import com.ldg.main.Repository.AdRepository;
import com.ldg.main.Services.AdService;
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

    // Public route
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(adService.findAll());
    }

    // Public route
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") long id) {
        Optional<Ad> optionalAd = adRepository.findById(id);
        if (optionalAd.isPresent())
            return ResponseEntity.ok(optionalAd.get());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
    }

    @GetMapping("/my") // get from token
    public ResponseEntity<?> myAds() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        return ResponseEntity.status(HttpStatus.OK).body(adRepository.findMyAds(user.getID()));
    }

    @GetMapping("/other")
    public ResponseEntity<?> otherAds() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        return ResponseEntity.status(HttpStatus.OK).body(adRepository.findOtherAds(user.getID()));
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
