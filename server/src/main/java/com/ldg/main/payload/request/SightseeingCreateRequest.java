package com.ldg.main.payload.request;

import javax.validation.constraints.*;

public class SightseeingCreateRequest {
    @Min(0)
    public long adId = -1;

    @NotEmpty
    @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}", message = "Date must be in format yyyy-MM-dd hh:mm:ss")
    public String time;
}