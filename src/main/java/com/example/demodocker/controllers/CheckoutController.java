package com.example.demodocker.controllers;

import com.example.demodocker.dto.OrderRequest;
import com.example.demodocker.entity.Order;
import com.example.demodocker.services.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @PostMapping("/checkout/{userId}")
    public Order placeOrder(@PathVariable("userId") Integer userId, @RequestBody OrderRequest orderRequest){
        return checkoutService.checkout(userId, orderRequest);
    }
}
