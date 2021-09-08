package com.example.demodocker.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demodocker.entity.Author;
import com.example.demodocker.entity.CartItem;
import com.example.demodocker.entity.Course;
import com.example.demodocker.entity.CourseImage;
import com.example.demodocker.entity.Interest;
import com.example.demodocker.entity.Role;
import com.example.demodocker.entity.User;
import com.example.demodocker.enums.Experience;
import com.example.demodocker.enums.Profession;
import com.example.demodocker.exceptions.ProductAlreadyInTheCartException;
import com.example.demodocker.repositories.CartItemRepository;

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

@ContextConfiguration(classes = {CourseService.class, CartItemService.class, UserService.class})
@ExtendWith(SpringExtension.class)
public class CartItemServiceTest {
    @MockBean
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartItemService cartItemService;

    @MockBean
    private CourseService courseService;

    @MockBean
    private UserService userService;

    @Test
    public void testAddCourse() {
        User user = new User();
        user.setLastName("Singh");
        user.setEmail("abc@example.com");
        user.setPassword("1230000");
        user.setInterests(new HashSet<Interest>());
        user.setBio("Biography of Pradeep singh");
        user.setId(123L);
        user.setEnabled(true);
        user.setFirstName("Pradeep");
        user.setImage("image.png");
        user.setRoles(new HashSet<Role>());
        user.setProfession(Profession.STUDENT);
        user.setExperience(Experience.ZERO_TWO);
        when(this.userService.getUserById((Long) any())).thenReturn(user);

        Author author = new Author();
        author.setAuthorName("Brad");
        author.setAuthorImage("brad.png");
        author.setId(1);
        author.setAuthorDescription("Brad is a good instructor");

        Interest interest = new Interest();
        interest.setId(1);
        interest.setInterestArea("backend");

        Course course = new Course();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        course.setDiscountLastDate(Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant()));
        course.setAuthor(author);
        course.setShortDescription("very short description........");
        course.setDiscountPercentage(10.0f);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        course.setUpdatedTime(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
        course.setName("spring boot advanced");
        course.setImages(new HashSet<CourseImage>());
        course.setReviewCount(3);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        course.setCreatedTime(Date.from(atStartOfDayResult2.atZone(ZoneId.systemDefault()).toInstant()));
        course.setMainImage("main-img.png");
        course.setId(123L);
        course.setFullDescription("full description");
        course.setCost(10.0f);
        course.setCategory(interest);
        course.setAverageRating(10.0f);
        when(this.courseService.getById((Integer) any())).thenReturn(course);

        User user1 = new User();
        user1.setLastName("Gaurav");
        user1.setEmail("gaurav@example.org");
        user1.setPassword("1234");
        user1.setInterests(new HashSet<Interest>());
        user1.setBio("Biography of a saint");
        user1.setId(123L);
        user1.setEnabled(true);
        user1.setFirstName("Singh");
        user1.setImage("image.png");
        user1.setRoles(new HashSet<Role>());
        user1.setProfession(Profession.STUDENT);
        user1.setExperience(Experience.ZERO_TWO);

        Author author1 = new Author();
        author1.setAuthorName("NarayanSingh");
        author1.setAuthorImage("janedoe.png");
        author1.setId(1);
        author1.setAuthorDescription("janedoe");

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

        CartItem cartItem = new CartItem();
        cartItem.setUser(user1);
        cartItem.setCourse(course1);
        cartItem.setId(1);
        when(this.cartItemRepository.findByCourseAndUser((Course) any(), (User) any())).thenReturn(cartItem);
        assertThrows(ProductAlreadyInTheCartException.class, () -> this.cartItemService.addCourse(123, 1L));
        verify(this.userService).getUserById((Long) any());
        verify(this.courseService).getById((Integer) any());
        verify(this.cartItemRepository).findByCourseAndUser((Course) any(), (User) any());
    }

}

