package com.ldg.main.Controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ldg.main.Models.Ad;
import com.ldg.main.Repository.AdRepository;

@RestController
@RequestMapping("/api/ads")
public class AdController {
    @Autowired
    AdRepository adRepository;

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok(adRepository.findAll());
        } catch (Exception e) {
            Map<String, String> map = new HashMap<>();
            map.put("message", "INTERNAL SERVER ERROR");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") long id) {
        Optional<Ad> optionalAd = adRepository.findById(id);
        if (optionalAd.isPresent())
            return ResponseEntity.ok(optionalAd.get());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
    }

    @GetMapping("/my/{id}") // get from token
    public ResponseEntity<?> myAds(@PathVariable("id") long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adRepository.findMyAds(id));
        } catch (Exception exception) {
            Map<String, String> map = new HashMap<>();
            map.put("message", "INTERNAL SERVER ERROR");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

    @GetMapping("/other/{id}")
    public ResponseEntity<?> otherAds(@PathVariable("id") long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adRepository.findOtherAds(id));
        } catch (Exception exception) {
            Map<String, String> map = new HashMap<>();
            map.put("message", "INTERNAL SERVER ERROR");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

    @PostMapping // ownerId is part of request body
    public ResponseEntity<?> store(@Valid @RequestBody Ad ad) {
        try {
            adRepository.save(ad);
            Map<String, String> map = new HashMap<>();
            map.put("message", "Stored");
            return ResponseEntity.status(HttpStatus.CREATED).body(map);
        } catch (Exception exception) {
            Map<String, String> map = new HashMap<>();
            map.put("message", "INTERNAL SERVER ERROR");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

    @PatchMapping // ownerId is part of request body
    public ResponseEntity<?> update(@Valid @RequestBody Ad ad) {
        try {
            adRepository.save(ad);
            Map<String, String> map = new HashMap<>();
            map.put("message", "Updated");
            return ResponseEntity.status(HttpStatus.OK).body(map);
        } catch (Exception exception) {
            Map<String, String> map = new HashMap<>();
            map.put("message", "INTERNAL SERVER ERROR");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

    @DeleteMapping("/{id}") // have permission to delete
    public ResponseEntity<?> destroy(@PathVariable("id") long id) {
        try {
            Ad ad = adRepository.getReferenceById(id);
            adRepository.deleteById(id);
            Map<String, String> map = new HashMap<>();
            map.put("message", "Deleted");
            return ResponseEntity.status(HttpStatus.OK).body(map);
        } catch (Exception exception) {
            Map<String, String> map = new HashMap<>();
            map.put("message", "NOT FOUND");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
    }

}
