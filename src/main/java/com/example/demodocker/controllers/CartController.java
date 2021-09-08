package com.example.demodocker.controllers;


import com.example.demodocker.dto.CartOverview;
import com.example.demodocker.dto.CartViewResponse;
import com.example.demodocker.dto.GeneralResponse;
import com.example.demodocker.entity.CartItem;
import com.example.demodocker.entity.User;
import com.example.demodocker.services.CartItemService;
import com.example.demodocker.services.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private UserService userService;

    @PostMapping("/cart/add/{courseid}/{userid}")
    @ApiOperation(
            value = "Add a course to the cart",
            notes = "The API adds a course associated to a particular user into the cart.",
            response = CartItem.class
    )
    public CartItem addCourseToCart(@PathVariable("courseid") Integer courseid, @PathVariable("userid") Long userid) {
        return cartItemService.addCourse(courseid, userid);
    }

    @GetMapping("/cart/retrieve/{userId}")
    @ApiOperation(
            value = "Retrieves cart for a particular user",
            notes = "The API returns the list of cart items with essential information only.",
            response = CartViewResponse.class
    )
    public CartOverview viewCartForUser(@PathVariable("userId") Long userId) {
        User user = userService.getUserById(userId);
        List<CartItem> cartItems = cartItemService.listCartItemsByUser(user);
        List<CartViewResponse> finalCart = new ArrayList<>();
        float total = 0;
        for (CartItem cartItem : cartItems) {
            finalCart.add(new CartViewResponse(cartItem.getCourse().getName(), cartItem.getCourse().getMainImage(),
                    cartItem.getCourse().getShortDescription(), cartItem.getCourse().getAuthor().getAuthorName(),
                    cartItem.getCourse().getCategory().getInterestArea(), cartItem.getCourse().getCost(),
                    cartItem.getCourse().getDiscountPercentage()));
            total += cartItem.getCourse().getCost() - (cartItem.getCourse().getDiscountPercentage() * cartItem.getCourse().getCost()) / 100;
        }
        return new CartOverview(total, finalCart);
    }

    @DeleteMapping("/cart/remove/{courseId}/{userId}")
    @ApiOperation(
            value = "Removes cart item associated with the given courseId and userId.",
            notes = "The API removes the cart item associated with the given user and course and throws exception if the cart is empty."
    )
    public ResponseEntity<GeneralResponse> removeItemFromCart(@PathVariable("courseId") Integer courseId, @PathVariable("userId") Long userId) {
        cartItemService.removeCourse(courseId, userId);
        return new ResponseEntity<>(new GeneralResponse("Item successfully removed from cart"), HttpStatus.OK);
    }

    @GetMapping(value = "/cart/pdfexport/{userId}", produces = MediaType.APPLICATION_PDF_VALUE)
    @ApiOperation(
            value = "Generates PDF of a given user's cart",
            notes = "The API takes user id as an input and generates a pdf showing the cart overview of the user."
    )
    public ResponseEntity<InputStreamResource> cartOverview(@PathVariable("userId") Long userId) throws IOException {
        User user = userService.getUserById(userId);
        List<CartItem> cartItems = cartItemService.listCartItemsByUser(user);
        List<CartViewResponse> finalCart = new ArrayList<>();
        float total = 0;
        for (CartItem cartItem : cartItems) {
            finalCart.add(new CartViewResponse(cartItem.getCourse().getName(), cartItem.getCourse().getMainImage(),
                    cartItem.getCourse().getShortDescription(), cartItem.getCourse().getAuthor().getAuthorName(),
                    cartItem.getCourse().getCategory().getInterestArea(), cartItem.getCourse().getCost(),
                    cartItem.getCourse().getDiscountPercentage()));
            total += cartItem.getCourse().getCost() - (cartItem.getCourse().getDiscountPercentage() * cartItem.getCourse().getCost()) / 100;
        }
        ByteArrayInputStream bis = cartItemService.generatePdfReport(total, finalCart);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename = cartReport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }


}

