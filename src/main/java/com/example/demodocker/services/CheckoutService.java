package com.example.demodocker.services;

import com.example.demodocker.dto.OrderRequest;
import com.example.demodocker.entity.CartItem;
import com.example.demodocker.entity.Order;
import com.example.demodocker.entity.OrderDetails;
import com.example.demodocker.entity.User;
import com.example.demodocker.enums.PaymentMethod;
import com.example.demodocker.exceptions.CartEmptyException;
import com.example.demodocker.exceptions.TechnicalFailureDuringCheckout;
import com.example.demodocker.repositories.OrderDetailsRepository;
import com.example.demodocker.repositories.OrderRepository;
import com.example.demodocker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CheckoutService {
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private UserRepository userRepository;
    @Autowired OrderRepository orderRepository;
    @Autowired OrderDetailsRepository orderDetailsRepository;

    public Order checkout(Integer id, OrderRequest orderRequest) {
        User user = userRepository.getById(Long.valueOf(id));
        List<CartItem> cartItems = cartItemService.listCartItemsByUser(user);
        if (cartItems.size() == 0) {
            throw new CartEmptyException("Cart is empty. Cannot perform checkout.");
        }
        try {
            List<OrderDetails> itemsInOrder = new ArrayList<>();
            float total = 0;
            for (CartItem item : cartItems) {
                OrderDetails orderDetails = new OrderDetails(item.getCourse().getCost(), item.getCourse().getDiscountPercentage(), item.getCourse());
                OrderDetails savedOrderDetails = orderDetailsRepository.save(orderDetails);
                itemsInOrder.add(savedOrderDetails);
                total = total + (savedOrderDetails.getCourse().getCost() - (savedOrderDetails.getCourse().getDiscountPercentage() * savedOrderDetails.getCourse().getCost()) / 100);
            }
            Order newOrder = new Order();
            newOrder.getOrderDetails().addAll(itemsInOrder);
            newOrder.setOrderCost(total);
            newOrder.setTax(5);
            newOrder.setEmail(user.getEmail());
            newOrder.setOrderTime(new Date());
            newOrder.setFirstName(user.getFirstName());
            newOrder.setLastName(user.getLastName());
            newOrder.setTotal((float) (total + 0.05 * total));
            newOrder.setUser(user);
            newOrder.setPaymentMethod(PaymentMethod.valueOf(orderRequest.getPaymentMethod().toString()));
            orderRepository.save(newOrder);
            return newOrder;
        } catch (TechnicalFailureDuringCheckout e) {
            throw new TechnicalFailureDuringCheckout("Checkout cannot be completed due to some technical failures");
        }
    }
}
