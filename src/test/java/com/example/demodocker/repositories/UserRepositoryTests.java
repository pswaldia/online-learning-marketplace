package com.example.demodocker.repositories;

import com.example.demodocker.entity.*;
import com.example.demodocker.enums.Experience;
import com.example.demodocker.enums.Profession;
import com.example.demodocker.enums.Rating;
import com.example.demodocker.repositories.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRepositoryTests {
    @Autowired private UserRepository userRepository;
    @Autowired private InterestRepository interestRepository;
    @Autowired private RoleRepository roleRepository;
    @Autowired private AuthorRepository authorRepository;
    @Autowired private CourseRepository courseRepository;
    @Autowired private CartItemRepository cartItemRepository;
    @Autowired private ReviewRepository reviewRepository;

    @Test
    @Order(1)
    public void testCreateTwoInterests() {
        Interest literature = new Interest("Literature");
        Interest savedInterest = interestRepository.save(literature);
        Interest ethicalHacking = new Interest("Ethical Hacking");
        Interest savedInterest2 = interestRepository.save(ethicalHacking);
        Assertions.assertThat(savedInterest.getId()).isGreaterThan(0);
        Assertions.assertThat(savedInterest2.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void testCreateTwoRoles() {
        Role roleAdmin = new Role("Admin", "manages entire platform");
        Role savedRole = roleRepository.save(roleAdmin);
        Assertions.assertThat(savedRole.getId()).isGreaterThan(0);
        Role roleEditor = new Role("Editor", "manages editing of course items, catalogs etc.");
        Role savedRole2 = roleRepository.save(roleEditor);
        Assertions.assertThat(savedRole2.getId()).isGreaterThan(0);
    }

    @Test
    @Order(3)
    public void testCreateUser() {
        Role roleAdmin = roleRepository.findById(1).get();
        Interest interest = interestRepository.findById(1).get();
        User userPradeep = new User("pswaldia1@gmail.com", "Pradeep", "Singh", "Password01", "SDE @Hashedin , Ex-KPMG, Ex-Accolite", Experience.ZERO_TWO, Profession.PROFESSIONAL);
        userPradeep.getRoles().add(roleAdmin);
        userPradeep.getInterests().add(interest);
        User savedUser = userRepository.save(userPradeep);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    public void testCreateUserWithTwoRoles() {
        User userGaurav = new User("gswaldia25@gmail.com", "Gaurav", "Singh", "Password02", "Undergrad @Panjab-University, ECE undergrad", Experience.ZERO_TWO, Profession.STUDENT);
        Role roleEditor = roleRepository.findById(2).get();
        Role roleAdmin = roleRepository.findById(1).get();
        Interest backendDeveloper = interestRepository.findById(1).get();
        userGaurav.getInterests().add(backendDeveloper);
        userGaurav.getRoles().add(roleAdmin);
        userGaurav.getRoles().add(roleEditor);
        User savedUser = userRepository.save(userGaurav);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    @Order(5)
    public void testListAllUsers() {
        assertThat(userRepository.findAll().size()).isEqualTo(2);
    }

    @Test
    @Order(6)
    public void testGetUserById() {
        User user = userRepository.findById(1L).get();
        assertThat(user).isNotNull();
    }

    @Test
    @Order(7)
    public void testAddAuthor() {
        Author newAuthor = new Author("Brad Traversy", "Frontend Developer with 12+ years of experience in the industry and 6+ years of corporate training. Now a full time content " +
                "creator with an aim to help the community learn and grow.");
        Author savedAuthor = authorRepository.save(newAuthor);
        assertThat(savedAuthor.getId()).isGreaterThan(0);
    }

    @Test
    @Order(8)
    void testAddOtherAuthors() {
        Author newAuthor1 = new Author("Colt Steele", "Fullstack Developer with 10+ years of teaching and industry. Master of MERN technology stack and have so far taught 1200 happy students. " +
                "Can't wait for you to join this rocket ride to the world of knowledge. ");
        Author newAuthor2 = new Author("Shiv Khera", "Leadership and personality development coach with a decade of experience in conducting leadership workshops and seminars. Now an author" +
                "with 2 best sellers.");
        Author newAuthor3 = new Author("Robin Sharma", "Best seller author . Author of : The Man who Sold His Ferrari. Now a content creator");
        authorRepository.saveAll(List.of(newAuthor1, newAuthor2, newAuthor3));
    }

    @Test
    @Order(9)
    public void testRetrieveById() {
        Author author = authorRepository.findById(1).get();
        assertThat(author).isNotNull();
    }

    @Test
    @Order(10)
    public void testCreateProduct() {
        Author author = authorRepository.findById(1).get();
        Interest interest = interestRepository.findById(2).get();
        Course course = new Course("React.Js Masterclass", "React.js course for beginner", "Build Single Page Application using React.Js framework with" +
                "industry standard practices from a instructor with 12+ years of experience in teaching.", new Date(), new Date(), 1299, 10, new Date(2021, Calendar.NOVEMBER, 28), interest, author);
        Course savedCourse = courseRepository.save(course);
        Assertions.assertThat(savedCourse.getId()).isGreaterThan(0);
    }

    @Test
    @Order(11)
    public void testSaveTwoItem() {
        Long userId1 = 1L;
        Long courseId1 = 1L;

        User user1 = userRepository.findById(userId1).get();
        Course course = courseRepository.findById(courseId1).get();
        CartItem newItem1 = new CartItem();
        newItem1.setUser(user1);
        newItem1.setCourse(course);
        cartItemRepository.save(newItem1);
        Long userId2 = 2L;
        Long courseId2 = 1L;
        User user2 = userRepository.findById(userId2).get();
        Course course2 = courseRepository.findById(courseId2).get();
        CartItem newItem2 = new CartItem();
        newItem2.setUser(user2);
        newItem2.setCourse(course2);
        cartItemRepository.save(newItem2);
        Assertions.assertThat(cartItemRepository.findAll().size()).isEqualTo(2);
    }

    @Test
    @Order(12)
    public void testAddTwoReviews() {
        Long userId1 = 1L;
        Long courseId1 = 1L;

        Course course1 = courseRepository.findById(courseId1).get();
        User user1 = userRepository.findById(userId1).get();
        Reviews newReview1 = new Reviews();
        newReview1.setReview("A very knowledgeable course indeed. Instructor is really good. Would recommend it to all the learners out there.");
        newReview1.setRating(Rating.FIVE);
        newReview1.setCourse(course1);
        newReview1.setUser(user1);
        int reviewCount = course1.getReviewCount();
        float averageReview = course1.getAverageRating();
        course1.setAverageRating(averageReview + Rating.FOUR.ordinal() + 1);
        course1.setReviewCount(reviewCount + Rating.FIVE.ordinal() + 1);
        Reviews savedReview1 = reviewRepository.save(newReview1);
        Assertions.assertThat(savedReview1.getId()).isGreaterThan(0);
    }
}
