package com.ldg.main.payload.request;

import javax.validation.constraints.*;

public class SightSeeingMarkAndCommentRequest {
    @Min(1)
    @Max(5)
    public int mark;

    @NotEmpty
    public String comment;
}
