package com.example.demodocker.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demodocker.dto.ReviewRequest;
import com.example.demodocker.dto.ReviewResponse;
import com.example.demodocker.entity.Author;
import com.example.demodocker.entity.Course;
import com.example.demodocker.entity.CourseImage;
import com.example.demodocker.entity.Interest;
import com.example.demodocker.entity.Reviews;
import com.example.demodocker.entity.Role;
import com.example.demodocker.entity.User;
import com.example.demodocker.enums.Experience;
import com.example.demodocker.enums.Profession;
import com.example.demodocker.enums.Rating;
import com.example.demodocker.exceptions.OnceReviewedCannotBeChanged;
import com.example.demodocker.repositories.ReviewRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CourseService.class, UserService.class, ReviewService.class})
@ExtendWith(SpringExtension.class)
public class ReviewServiceTest {
    @MockBean
    private CourseService courseService;

    @MockBean
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewService reviewService;

    @MockBean
    private UserService userService;

    @Test
    public void testAddReview() {
        User user = new User();
        user.setLastName("Doe");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setInterests(new HashSet<Interest>());
        user.setBio("Bio");
        user.setId(123L);
        user.setEnabled(true);
        user.setFirstName("Jane");
        user.setImage("Image");
        user.setRoles(new HashSet<Role>());
        user.setProfession(Profession.STUDENT);
        user.setExperience(Experience.ZERO_TWO);
        when(this.userService.getUserById((Long) any())).thenReturn(user);

        User user1 = new User();
        user1.setLastName("Doe");
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setInterests(new HashSet<Interest>());
        user1.setBio("Bio");
        user1.setId(123L);
        user1.setEnabled(true);
        user1.setFirstName("Jane");
        user1.setImage("Image");
        user1.setRoles(new HashSet<Role>());
        user1.setProfession(Profession.STUDENT);
        user1.setExperience(Experience.ZERO_TWO);

        Author author = new Author();
        author.setAuthorName("JaneDoe");
        author.setAuthorImage("JaneDoe");
        author.setId(1);
        author.setAuthorDescription("JaneDoe");

        Interest interest = new Interest();
        interest.setId(1);
        interest.setInterestArea("Interest Area");

        Course course = new Course();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        course.setDiscountLastDate(Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant()));
        course.setAuthor(author);
        course.setShortDescription("Short Description");
        course.setDiscountPercentage(10.0f);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        course.setUpdatedTime(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
        course.setName("Name");
        course.setImages(new HashSet<CourseImage>());
        course.setReviewCount(3);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        course.setCreatedTime(Date.from(atStartOfDayResult2.atZone(ZoneId.systemDefault()).toInstant()));
        course.setMainImage("Main Image");
        course.setId(123L);
        course.setFullDescription("Full Description");
        course.setCost(10.0f);
        course.setCategory(interest);
        course.setAverageRating(10.0f);

        Reviews reviews = new Reviews();
        reviews.setUser(user1);
        reviews.setCourse(course);
        reviews.setReview("Review");
        reviews.setId(123L);
        reviews.setRating(Rating.ONE);
        when(this.reviewRepository.findByUserAndCourse((User) any(), (Course) any())).thenReturn(reviews);

        Author author1 = new Author();
        author1.setAuthorName("JaneDoe");
        author1.setAuthorImage("JaneDoe");
        author1.setId(1);
        author1.setAuthorDescription("JaneDoe");

        Interest interest1 = new Interest();
        interest1.setId(1);
        interest1.setInterestArea("Interest Area");

        Course course1 = new Course();
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        course1.setDiscountLastDate(Date.from(atStartOfDayResult3.atZone(ZoneId.systemDefault()).toInstant()));
        course1.setAuthor(author1);
        course1.setShortDescription("Short Description");
        course1.setDiscountPercentage(10.0f);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        course1.setUpdatedTime(Date.from(atStartOfDayResult4.atZone(ZoneId.systemDefault()).toInstant()));
        course1.setName("Name");
        course1.setImages(new HashSet<CourseImage>());
        course1.setReviewCount(3);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        course1.setCreatedTime(Date.from(atStartOfDayResult5.atZone(ZoneId.systemDefault()).toInstant()));
        course1.setMainImage("Main Image");
        course1.setId(123L);
        course1.setFullDescription("Full Description");
        course1.setCost(10.0f);
        course1.setCategory(interest1);
        course1.setAverageRating(10.0f);
        when(this.courseService.getById((Integer) any())).thenReturn(course1);
        assertThrows(OnceReviewedCannotBeChanged.class,
                () -> this.reviewService.addReview(123, 123L, new ReviewRequest(Rating.ONE, "Review")));
        verify(this.userService).getUserById((Long) any());
        verify(this.reviewRepository).findByUserAndCourse((User) any(), (Course) any());
        verify(this.courseService).getById((Integer) any());
    }

    @Test
    public void testFetchReviewByUser() {
        User user = new User();
        user.setLastName("Doe");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setInterests(new HashSet<Interest>());
        user.setBio("Bio");
        user.setId(123L);
        user.setEnabled(true);
        user.setFirstName("Jane");
        user.setImage("Image");
        user.setRoles(new HashSet<Role>());
        user.setProfession(Profession.STUDENT);
        user.setExperience(Experience.ZERO_TWO);
        when(this.userService.getUserById((Long) any())).thenReturn(user);
        when(this.reviewRepository.findByUser((User) any())).thenReturn(new ArrayList<Reviews>());
        assertTrue(this.reviewService.fetchReviewByUser(123L).isEmpty());
        verify(this.userService).getUserById((Long) any());
        verify(this.reviewRepository).findByUser((User) any());
    }

    @Test
    public void testFetchReviewByUser2() {
        User user = new User();
        user.setLastName("Doe");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setInterests(new HashSet<Interest>());
        user.setBio("Bio");
        user.setId(123L);
        user.setEnabled(true);
        user.setFirstName("Jane");
        user.setImage("Image");
        user.setRoles(new HashSet<Role>());
        user.setProfession(Profession.STUDENT);
        user.setExperience(Experience.ZERO_TWO);
        when(this.userService.getUserById((Long) any())).thenReturn(user);

        User user1 = new User();
        user1.setLastName("Doe");
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setInterests(new HashSet<Interest>());
        user1.setBio("Bio");
        user1.setId(123L);
        user1.setEnabled(true);
        user1.setFirstName("Jane");
        user1.setImage("Image");
        user1.setRoles(new HashSet<Role>());
        user1.setProfession(Profession.STUDENT);
        user1.setExperience(Experience.ZERO_TWO);

        Author author = new Author();
        author.setAuthorName("JaneDoe");
        author.setAuthorImage("JaneDoe");
        author.setId(1);
        author.setAuthorDescription("JaneDoe");

        Interest interest = new Interest();
        interest.setId(1);
        interest.setInterestArea("Interest Area");

        Course course = new Course();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        course.setDiscountLastDate(Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant()));
        course.setAuthor(author);
        course.setShortDescription("Short Description");
        course.setDiscountPercentage(10.0f);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        course.setUpdatedTime(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
        course.setName("Name");
        course.setImages(new HashSet<CourseImage>());
        course.setReviewCount(3);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        course.setCreatedTime(Date.from(atStartOfDayResult2.atZone(ZoneId.systemDefault()).toInstant()));
        course.setMainImage("Main Image");
        course.setId(123L);
        course.setFullDescription("Full Description");
        course.setCost(10.0f);
        course.setCategory(interest);
        course.setAverageRating(10.0f);

        Reviews reviews = new Reviews();
        reviews.setUser(user1);
        reviews.setCourse(course);
        reviews.setReview("Review");
        reviews.setId(123L);
        reviews.setRating(Rating.ONE);

        ArrayList<Reviews> reviewsList = new ArrayList<Reviews>();
        reviewsList.add(reviews);
        when(this.reviewRepository.findByUser((User) any())).thenReturn(reviewsList);
        List<ReviewResponse> actualFetchReviewByUserResult = this.reviewService.fetchReviewByUser(123L);
        assertEquals(1, actualFetchReviewByUserResult.size());
        ReviewResponse getResult = actualFetchReviewByUserResult.get(0);
        assertEquals("Name", getResult.getCourseName());
        assertEquals("Review", getResult.getReview());
        assertEquals(Rating.ONE, getResult.getRating());
        verify(this.userService).getUserById((Long) any());
        verify(this.reviewRepository).findByUser((User) any());
    }

    @Test
    public void testFetchReviewByUser3() {
        User user = new User();
        user.setLastName("Doe");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setInterests(new HashSet<Interest>());
        user.setBio("Bio");
        user.setId(123L);
        user.setEnabled(true);
        user.setFirstName("Jane");
        user.setImage("Image");
        user.setRoles(new HashSet<Role>());
        user.setProfession(Profession.STUDENT);
        user.setExperience(Experience.ZERO_TWO);
        when(this.userService.getUserById((Long) any())).thenReturn(user);

        User user1 = new User();
        user1.setLastName("Doe");
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setInterests(new HashSet<Interest>());
        user1.setBio("Bio");
        user1.setId(123L);
        user1.setEnabled(true);
        user1.setFirstName("Jane");
        user1.setImage("Image");
        user1.setRoles(new HashSet<Role>());
        user1.setProfession(Profession.STUDENT);
        user1.setExperience(Experience.ZERO_TWO);

        Author author = new Author();
        author.setAuthorName("JaneDoe");
        author.setAuthorImage("JaneDoe");
        author.setId(1);
        author.setAuthorDescription("JaneDoe");

        Interest interest = new Interest();
        interest.setId(1);
        interest.setInterestArea("Interest Area");

        Course course = new Course();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        course.setDiscountLastDate(Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant()));
        course.setAuthor(author);
        course.setShortDescription("Short Description");
        course.setDiscountPercentage(10.0f);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        course.setUpdatedTime(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
        course.setName("Name");
        course.setImages(new HashSet<CourseImage>());
        course.setReviewCount(3);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        course.setCreatedTime(Date.from(atStartOfDayResult2.atZone(ZoneId.systemDefault()).toInstant()));
        course.setMainImage("Main Image");
        course.setId(123L);
        course.setFullDescription("Full Description");
        course.setCost(10.0f);
        course.setCategory(interest);
        course.setAverageRating(10.0f);

        Reviews reviews = new Reviews();
        reviews.setUser(user1);
        reviews.setCourse(course);
        reviews.setReview("Review");
        reviews.setId(123L);
        reviews.setRating(Rating.ONE);

        User user2 = new User();
        user2.setLastName("Doe");
        user2.setEmail("jane.doe@example.org");
        user2.setPassword("iloveyou");
        user2.setInterests(new HashSet<Interest>());
        user2.setBio("Bio");
        user2.setId(123L);
        user2.setEnabled(true);
        user2.setFirstName("Jane");
        user2.setImage("Image");
        user2.setRoles(new HashSet<Role>());
        user2.setProfession(Profession.STUDENT);
        user2.setExperience(Experience.ZERO_TWO);

        Author author1 = new Author();
        author1.setAuthorName("JaneDoe");
        author1.setAuthorImage("JaneDoe");
        author1.setId(1);
        author1.setAuthorDescription("JaneDoe");

        Interest interest1 = new Interest();
        interest1.setId(1);
        interest1.setInterestArea("Interest Area");

        Course course1 = new Course();
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        course1.setDiscountLastDate(Date.from(atStartOfDayResult3.atZone(ZoneId.systemDefault()).toInstant()));
        course1.setAuthor(author1);
        course1.setShortDescription("Short Description");
        course1.setDiscountPercentage(10.0f);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        course1.setUpdatedTime(Date.from(atStartOfDayResult4.atZone(ZoneId.systemDefault()).toInstant()));
        course1.setName("Name");
        course1.setImages(new HashSet<CourseImage>());
        course1.setReviewCount(3);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        course1.setCreatedTime(Date.from(atStartOfDayResult5.atZone(ZoneId.systemDefault()).toInstant()));
        course1.setMainImage("Main Image");
        course1.setId(123L);
        course1.setFullDescription("Full Description");
        course1.setCost(10.0f);
        course1.setCategory(interest1);
        course1.setAverageRating(10.0f);

        Reviews reviews1 = new Reviews();
        reviews1.setUser(user2);
        reviews1.setCourse(course1);
        reviews1.setReview("Review");
        reviews1.setId(123L);
        reviews1.setRating(Rating.ONE);

        ArrayList<Reviews> reviewsList = new ArrayList<Reviews>();
        reviewsList.add(reviews1);
        reviewsList.add(reviews);
        when(this.reviewRepository.findByUser((User) any())).thenReturn(reviewsList);
        List<ReviewResponse> actualFetchReviewByUserResult = this.reviewService.fetchReviewByUser(123L);
        assertEquals(2, actualFetchReviewByUserResult.size());
        ReviewResponse getResult = actualFetchReviewByUserResult.get(1);
        assertEquals("Review", getResult.getReview());
        assertEquals(Rating.ONE, getResult.getRating());
        assertEquals("Name", getResult.getCourseName());
        ReviewResponse getResult1 = actualFetchReviewByUserResult.get(0);
        assertEquals("Review", getResult1.getReview());
        assertEquals(Rating.ONE, getResult1.getRating());
        assertEquals("Name", getResult1.getCourseName());
        verify(this.userService).getUserById((Long) any());
        verify(this.reviewRepository).findByUser((User) any());
    }
}

