package com.quickfix.dto;

import lombok.Data;

@Data
public class ReviewRequest {

    private Long bookingId;

    private int rating;

    private String comment;

}
