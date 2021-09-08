package com.example.demodocker.services;

import com.example.demodocker.dto.ReviewRequest;
import com.example.demodocker.dto.ReviewResponse;
import com.example.demodocker.entity.Course;
import com.example.demodocker.entity.Reviews;
import com.example.demodocker.entity.User;
import com.example.demodocker.exceptions.OnceReviewedCannotBeChanged;
import com.example.demodocker.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    public Reviews addReview(Integer courseId, Long userId, ReviewRequest reviewRequest) {
        Course course = courseService.getById(courseId);
        User user = userService.getUserById(userId);
        Reviews review = reviewRepository.findByUserAndCourse(user, course);
        if (review != null) {
            throw new OnceReviewedCannotBeChanged("Review for " + course.getName() + " has already been posted by " + user.getFirstName());
        }
        Reviews newReview = new Reviews();
        newReview.setCourse(course);
        newReview.setUser(user);
        newReview.setRating(reviewRequest.getRating());
        newReview.setReview(reviewRequest.getReview());
        Reviews savedReview = reviewRepository.save(newReview);
        return savedReview;
    }

    public List<ReviewResponse> fetchReviewByUser(Long userId){
        User user = userService.getUserById(userId);
        List<Reviews> reviews = reviewRepository.findByUser(user);
        List<ReviewResponse> reviewsByUser = new ArrayList<>();
        reviews.forEach(review -> reviewsByUser.add(new ReviewResponse(review.getCourse().getName(), review.getRating(), review.getReview())));
        return reviewsByUser;
    }
}
