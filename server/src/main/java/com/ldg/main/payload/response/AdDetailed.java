package com.ldg.main.payload.response;

import com.ldg.main.Models.*;
import java.util.*;

public class AdDetailed extends AdForVisitor {
    public List<Comment> comments;

    public AdDetailed(Ad ad, AdCategory adCategory, User owner, City city, Country country, long numberOfLikes) {
        super(ad, adCategory, owner, city, country, numberOfLikes);
        comments = new ArrayList<>();
    }

}
