package com.example.demodocker.dto;

import com.example.demodocker.enums.Rating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponse {
    private String courseName;
    private Rating rating;
    private String review;
}
