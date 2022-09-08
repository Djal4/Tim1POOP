package com.ldg.main.Services.Jobs.DynamicUpdaters;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ldg.main.Repository.UserRepository;
import com.ldg.main.Services.AuthService;
import com.ldg.main.Models.Ad;
import com.ldg.main.Models.Sightseeing;
import com.ldg.main.Models.User;
import com.ldg.main.Repository.AdRepository;
import com.ldg.main.Repository.SightseeingRepository;

@Component
public class Updater {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthService authService;
    @Autowired
    private AdRepository adRepository;
    @Autowired
    private SightseeingRepository sightseeingRepository;

    public ResponseEntity<?> update(Long ID, Object obj) {
        if (obj instanceof User)
            this.updateUser(ID, (User) obj);
        if (obj instanceof Sightseeing)
            this.updateSightsee(ID, (Sightseeing) obj);
        if (obj instanceof Ad)
            this.updateAd(ID, (Ad) obj);

        if (obj == null)
            return ResponseEntity.status(400).body("Wrong data.");
        return ResponseEntity.ok(obj);
    }

    public User updateUser(Long ID, User user) {
        User user2;
        Optional<User> usr = userRepository.findById(ID);
        if (usr.isPresent())
            if (authService.checkPassword(user.getPassword(), usr.get().getPassword())) {
                user2 = usr.get();
                if (!isEmpty(user.getFirstname()))
                    user2.setFirstname(user.getFirstname());
                if (!isEmpty(user.getLastname()))
                    user2.setLastname(user.getLastname());
                if (!isEmpty(user.getUsername()))
                    user2.setUsername(user.getUsername());

                // !SECURITY TODO!!!

                if (!isEmpty(user2.getRoleID()))
                    user.setRoleID(user2.getRoleID());

                userRepository.save(user2);
                return user2;
            }
        return null;
    }

    public Ad updateAd(Long ID, Ad ad) {
        Ad ad2;
        Optional<Ad> add = adRepository.findById(ID);
        if (add.isPresent()) {
            ad2 = add.get();
            if (!isEmpty(ad.getDescription()))
                ad2.setDescription(ad.getDescription());
            // if (!isEmpty(ad.getAdCategoryId()))
            // ad2.setAdCategoryId(ad.getAdCategoryId());
            if (!isEmpty(ad.getArea()))
                ad2.setArea(ad.getArea());
            if (!isEmpty(ad.getPrice()))
                ad2.setPrice(ad.getPrice());

            adRepository.save(ad2);

            return ad2;
        }
        return null;
    }

    public Sightseeing updateSightsee(Long ID, Sightseeing sigsee) {
        Sightseeing sig2;
        Optional<Sightseeing> sig = sightseeingRepository.findById(ID);
        if (sig.isPresent()) {
            sig2 = sig.get();
            if (sigsee.getAccepted())
                sig2.setAccepted(sigsee.getAccepted());
            if (!isEmpty(sigsee.getMark()))
                sig2.setMark(sigsee.getMark());
            if (sigsee.getTime() != null)
                sig2.setTime(sigsee.getTime());

            sightseeingRepository.save(sig2);
            return sig2;
        }
        return null;
    }

    public boolean isEmpty(String string) {
        return string == null || string == "";
    }

    public boolean isEmpty(Integer integer) {
        return integer == null;
    }

    public boolean isEmpty(Long l) {
        return l == null;
    }

    public boolean isEmpty(Float f) {
        return f == null;
    }

}
