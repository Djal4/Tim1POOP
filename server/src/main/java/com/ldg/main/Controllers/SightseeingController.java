package com.ldg.main.Controllers;

import java.util.*;
import java.text.*;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ldg.main.Models.Ad;
import com.ldg.main.Models.Sightseeing;
import com.ldg.main.Repository.AdRepository;
import com.ldg.main.Repository.SightseeingRepository;
import com.ldg.main.Services.SightseeingService;
import com.ldg.main.exceptions.HttpStatusCodeException;
import com.ldg.main.payload.request.SightSeeingMarkAndCommentRequest;
import com.ldg.main.payload.request.SightseeingCreateRequest;
import com.ldg.main.Models.UserDetailsImpl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/api/sightseeing")
public class SightseeingController {
    @Autowired
    SightseeingRepository sightseeingRepository;

    @Autowired
    AdRepository adRepository;

    @Autowired
    SightseeingService sightseeingService;

    /**
     * @apiNote Returns sightseeing for owner of advertisments.
     * @param id long
     * @return List<Sightseeing>
     */
    @GetMapping("/owner")
    public ResponseEntity<?> getByOwnerId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        return ResponseEntity.ok(sightseeingService.getOwnerSightseeings(user.getID()));
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
        return ResponseEntity.ok(sightseeingService.getUserSightseeings(user.getID()));
    }

    @PostMapping("/request")
    public ResponseEntity<?> requestSightseeing(@Valid @RequestBody SightseeingCreateRequest request)
            throws HttpStatusCodeException, ParseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        Optional<Ad> ad = adRepository.findById(request.adId);
        if (!ad.isPresent())
            throw new HttpStatusCodeException(HttpStatus.NOT_FOUND,
                    "Advertisment with id " + request.adId + " not exists!");
        if (user.getID() == ad.get().getOwnerId())
            throw new HttpStatusCodeException(HttpStatus.CONFLICT,
                    "User cannot request sightseeing for his adverisment!");
        Date now = new Date();
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date timeOfSightseeing = sdformat.parse(request.time);
        if (now.compareTo(timeOfSightseeing) > 0)
            throw new HttpStatusCodeException(HttpStatus.BAD_REQUEST, "Date must be in future");
        sightseeingRepository
                .save(new Sightseeing(user.getID(), request.adId, sdformat.format(timeOfSightseeing)));
        throw new HttpStatusCodeException(HttpStatus.CREATED, "Created");
    }

    private void acceptOrReject(long id, boolean b) throws HttpStatusCodeException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        Optional<Sightseeing> optional = sightseeingRepository.findById(id);
        if (optional.isPresent()) {
            Sightseeing s = optional.get();
            Optional<Ad> ad = adRepository.findById(s.getAdId());
            if (ad.get().getOwnerId() != user.getID())
                throw new HttpStatusCodeException(HttpStatus.FORBIDDEN,
                        "You can\'t accept this sightseeing because you\'re not owner.");
            if (s.getAccepted() != null)
                throw new HttpStatusCodeException(HttpStatus.CONFLICT,
                        "Sightseeing is already accepted or rejected");
            s.setAccepted(Boolean.valueOf(b));
            sightseeingRepository.save(s);
            String message;
            if (b)
                message = "Accepted";
            else
                message = "Rejected";
            throw new HttpStatusCodeException(HttpStatus.OK, message);
        }
        throw new HttpStatusCodeException(HttpStatus.NOT_FOUND,
                "Sightseeing not exists!");
    }

    @PatchMapping("/accept/{id}")
    public ResponseEntity<?> acceptSightseeing(@PathVariable("id") long id) throws HttpStatusCodeException {
        // if user is owner
        acceptOrReject(id, true);
        return null;
    }

    @PatchMapping("/reject/{id}")
    public ResponseEntity<?> rejectSightseeing(@PathVariable("id") long id) throws HttpStatusCodeException {
        // if user is owner
        acceptOrReject(id, false);
        return null;
    }

    @PatchMapping("/mark_and_comment/{id}")
    public ResponseEntity<?> markSightseeing(@PathVariable("id") long id,
            @Valid @RequestBody SightSeeingMarkAndCommentRequest request)
            throws HttpStatusCodeException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        Optional<Sightseeing> optional = sightseeingRepository.findById(id);
        if (optional.isPresent()) {
            Sightseeing s = optional.get();
            if (s.getUserId() != user.getID())
                throw new HttpStatusCodeException(HttpStatus.FORBIDDEN,
                        "You can\'t mark this sightseeing because you\'re not user who requested it!");
            if (s.getAccepted() != null && !s.getAccepted().booleanValue())
                throw new HttpStatusCodeException(HttpStatus.FORBIDDEN,
                        "You can\'t mark this sightseeing because it\'s not accepted!");
            s.setMark(request.mark);
            s.setComment(request.comment);
            sightseeingRepository.save(s);
            throw new HttpStatusCodeException(HttpStatus.OK, "User gave mark and comment.");
        }
        throw new HttpStatusCodeException(HttpStatus.NOT_FOUND,
                "Sightseeing not exists!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") long id) throws HttpStatusCodeException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        Optional<Sightseeing> optional = sightseeingRepository.findById(id);
        if (optional.isPresent()) {
            Sightseeing s = optional.get();
            if (s.getAccepted() != null && s.getAccepted())
                throw new HttpStatusCodeException(HttpStatus.FORBIDDEN, "You can\'t delete sightseeing");
            Optional<Ad> ad = adRepository.findById(s.getAdId());
            if (s.getUserId() != user.getID() && ad.get().getOwnerId() != user.getID())
                throw new HttpStatusCodeException(HttpStatus.FORBIDDEN,
                        "You can\'t mark this sightseeing because you\'re not owner.");
            sightseeingRepository.deleteById(id);
            throw new HttpStatusCodeException(HttpStatus.OK, "Deleted");
        }
        throw new HttpStatusCodeException(HttpStatus.NOT_FOUND, "Sightseeeing with id " + id + " not exists!");
    }

}
