package com.example.demodocker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartViewResponse {
    private String courseName;
    private String mainImage;
    private String courseShortDescription;
    private String authorName;
    private String category;
    private float price;
    private float discountPercentage;
}
