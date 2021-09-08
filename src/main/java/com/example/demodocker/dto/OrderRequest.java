package com.example.demodocker.dto;

import com.example.demodocker.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private PaymentMethod paymentMethod;
}
