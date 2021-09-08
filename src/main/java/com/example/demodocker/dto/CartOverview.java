package com.example.demodocker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartOverview {
    private float estimatedTotal;
    private List<CartViewResponse> cartItems;
}
