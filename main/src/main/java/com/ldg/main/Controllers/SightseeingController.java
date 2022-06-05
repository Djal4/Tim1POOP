package com.ldg.main.Controllers;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ldg.main.Models.Sightseeing;
import com.ldg.main.Repository.SightseeingRepository;

@RestController
@RequestMapping("/api/sightseeing")
public class SightseeingController {
    @Autowired
    SightseeingRepository sightseeingRepository;
    
    @PostMapping("/request")
    public ResponseEntity<?> requestSightseeing(@RequestParam("time") LocalDateTime time,@RequestParam("ad_id") long adId,@RequestParam("myId") long myId){
        try{
        return ResponseEntity.ok(sightseeingRepository.save(new Sightseeing(myId,adId,time)));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }
    @PutMapping("/accept/{id}")
    public ResponseEntity<?> acceptSightseeing(@PathVariable("id") long id){
        //if user is owner
        Optional<Sightseeing> optional=sightseeingRepository.findById(id);
        if(optional.isPresent())
        {
                Sightseeing s=optional.get();
                s.setAccepted(true);
                sightseeingRepository.save(s);
                return ResponseEntity.ok("");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") long id){
        sightseeingRepository.deleteById(id);
        return ResponseEntity.ok("");
    }

}
