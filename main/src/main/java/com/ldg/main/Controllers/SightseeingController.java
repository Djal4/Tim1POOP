package com.ldg.main.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ldg.main.Models.Sightseeing;
import com.ldg.main.Repository.SightseeingRepository;
import com.ldg.main.payload.request.SightseeingCreateRequest;


@RestController
@RequestMapping("/api/sightseeing")
public class SightseeingController {
    @Autowired
    SightseeingRepository sightseeingRepository;
    
    /**
    * @apiNote Returns sightseeing for owner of advertisments.
    * @param id long
    * @return List<Sightseeing>
    */
    @GetMapping("/owner/{id}")
    public ResponseEntity<?> getByOwnerId(@PathVariable("id") long id){
        return ResponseEntity.ok(sightseeingRepository.findByOwnerId(id));
    }
    /**
    * @apiNote Returns sightseeing for user who requested sightseeing.
    * @param id long
    * @return List<Sightseeing>
    */
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getByUserId(@PathVariable("id") long id){
        return ResponseEntity.ok(sightseeingRepository.findByUserId(id));
    }
    @PostMapping("/request")
    public ResponseEntity<?> requestSightseeing(@RequestBody SightseeingCreateRequest request){
        try{
        return ResponseEntity.ok(sightseeingRepository.save(new Sightseeing(request.myId,request.adId,request.time)));
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
    @PutMapping("/mark/{id}")
    public ResponseEntity<?> markSightseeing(@PathVariable("id") long id,@RequestBody float mark){
        //if user is owner
        Optional<Sightseeing> optional=sightseeingRepository.findById(id);
        if(optional.isPresent())
        {
                Sightseeing s=optional.get();
                s.setMark(mark);
                sightseeingRepository.save(s);
                return ResponseEntity.ok("");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") long id){
        sightseeingRepository.deleteById(id);
        return ResponseEntity.ok("DELETED");
    }

}
