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

import com.ldg.main.Models.Ad;
import com.ldg.main.Models.Sightseeing;
import com.ldg.main.Repository.AdRepository;
import com.ldg.main.Repository.SightseeingRepository;
import com.ldg.main.exceptions.HttpStatusCodeException;
import com.ldg.main.payload.request.SightseeingCreateRequest;
import com.ldg.main.Models.UserDetailsImpl;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/api/sightseeing")
public class SightseeingController {
    @Autowired
    SightseeingRepository sightseeingRepository;

    @Autowired
    AdRepository adRepository;

    /**
     * @apiNote Returns sightseeing for owner of advertisments.
     * @param id long
     * @return List<Sightseeing>
     */
    @GetMapping("/owner")
    public ResponseEntity<?> getByOwnerId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        return ResponseEntity.ok(sightseeingRepository.findByOwnerId(user.getID()));
    }

    /**
     * @apiNote Returns sightseeing for user who requested sightseeing.
     * @param id long
     * @return List<Sightseeing>
     */
    @GetMapping("/user")
    public ResponseEntity<?> getByUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        return ResponseEntity.ok(sightseeingRepository.findByUserId(user.getID()));
    }

    @PostMapping("/request")
    public ResponseEntity<?> requestSightseeing(@RequestBody SightseeingCreateRequest request)
            throws HttpStatusCodeException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        Optional<Ad> ad = adRepository.findById(request.adId);
        if (!ad.isPresent())
            throw new HttpStatusCodeException(HttpStatus.NOT_FOUND,
                    "Advertisment with id " + request.adId + " not exists!");
        if (user.getID() == ad.get().getOwnerId())
            throw new HttpStatusCodeException(HttpStatus.NOT_FOUND,
                    "User cannot request sightseeing for his adverisment!");
        return ResponseEntity
                .ok(sightseeingRepository.save(new Sightseeing(user.getID(), request.adId, request.time)));
    }

    @PutMapping("/accept/{id}")
    public ResponseEntity<?> acceptSightseeing(@PathVariable("id") long id) throws HttpStatusCodeException {
        // if user is owner
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        Optional<Sightseeing> optional = sightseeingRepository.findById(id);
        if (optional.isPresent()) {
            Sightseeing s = optional.get();
            Optional<Ad> ad = adRepository.findById(s.getAdId());
            if (ad.get().getOwnerId() != user.getID())
                throw new AccessDeniedException("You can\'t accept this sightseeing because you\'re not owner.");
            s.setAccepted(true);
            sightseeingRepository.save(s);
            throw new HttpStatusCodeException(HttpStatus.OK, "Accepted");
        }
        throw new HttpStatusCodeException(HttpStatus.NOT_FOUND,
                "Sightseeing not exists!");
    }

    @PutMapping("/mark/{id}")
    public ResponseEntity<?> markSightseeing(@PathVariable("id") long id, @RequestBody float mark)
            throws HttpStatusCodeException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        Optional<Sightseeing> optional = sightseeingRepository.findById(id);
        if (optional.isPresent()) {
            Sightseeing s = optional.get();
            if (s.getUserId() != user.getID())
                throw new AccessDeniedException(
                        "You can\'t mark this sightseeing because you\'re not user who requested it.");
            s.setMark(mark);
            sightseeingRepository.save(s);
            throw new HttpStatusCodeException(HttpStatus.OK, "User gave mark");
        }
        throw new HttpStatusCodeException(HttpStatus.NOT_FOUND,
                "Sightseeing not exists!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        Optional<Sightseeing> optional = sightseeingRepository.findById(id);
        if (optional.isPresent()) {
            Sightseeing s = optional.get();
            if (s.getAccepted())
                throw new AccessDeniedException("You can\'t delete sightseeing");
            Optional<Ad> ad = adRepository.findById(s.getAdId());
            if (s.getUserId() != user.getID() && ad.get().getOwnerId() != user.getID())
                throw new AccessDeniedException("You can\'t mark this sightseeing because you\'re not owner.");
            sightseeingRepository.deleteById(id);
            return ResponseEntity.ok("DELETED");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong");
    }

}
