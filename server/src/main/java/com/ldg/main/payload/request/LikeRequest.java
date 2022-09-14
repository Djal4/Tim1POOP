package com.ldg.main.payload.request;

import javax.validation.constraints.Min;

public class LikeRequest {
    @Min(0)
    public long adId;
}
