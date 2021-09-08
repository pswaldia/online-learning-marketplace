package com.example.demodocker.controllers;

import com.example.demodocker.dto.ReviewRequest;
import com.example.demodocker.dto.ReviewResponse;
import com.example.demodocker.entity.Reviews;
import com.example.demodocker.services.ReviewService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    Logger logger = LoggerFactory.getLogger(ReviewController.class);

    @PostMapping("/review/{courseId}/{userId}")
    @ApiOperation(
            value = "Add review of a particular course by a given user",
            notes = "The API adds the review (Rating and Review text) given by an user for a course represented by userId and courseId respectively",
            response = Reviews.class
    )
    public Reviews addReview(@PathVariable("courseId") Integer courseId, @PathVariable("userId") Long userId, @RequestBody ReviewRequest reviewRequest) {
        logger.info(String.valueOf(courseId));
        logger.info(String.valueOf(userId));
        Reviews reviews = reviewService.addReview(courseId, userId, reviewRequest);
        return reviews;
    }

    @GetMapping("/review/{userId}")
    @ApiOperation(
            value = "Get reviews posted by a given user.",
            notes = "The API returns the list of reviews posted by a particular user. The response contains course name, rating and review.",
            response = ReviewResponse.class
    )
    public List<ReviewResponse> findReviewsByUser(@PathVariable("userId") Long userId){
        return reviewService.fetchReviewByUser(userId);
    }
}
