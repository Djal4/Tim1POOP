package com.ldg.main.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ldg.main.Models.Ad;
import com.ldg.main.Repository.AdRepository;

@RestController
@RequestMapping("/api/ads")
public class AdController {
    @Autowired
    AdRepository adRepository;

    @GetMapping
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.ok(adRepository.findAll());
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body("");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") long id){
        Optional<Ad> optionalAd=adRepository.findById(id);
        if(optionalAd.isPresent())
            return ResponseEntity.ok(optionalAd.get());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
    }
    /* 
    @PostMapping
    public ResponseEntity<?> store(@RequestBody Ad ad){

    }
    */
    /*
    TODO:
        GET /other - returns other ads
        GET /my - returns my ads
     */
}
