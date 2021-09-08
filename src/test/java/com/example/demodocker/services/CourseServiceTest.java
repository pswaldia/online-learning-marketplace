package com.example.demodocker.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demodocker.dto.PaginatedBookResponse;
import com.example.demodocker.entity.Author;
import com.example.demodocker.entity.Course;
import com.example.demodocker.entity.CourseImage;
import com.example.demodocker.entity.Interest;
import com.example.demodocker.exceptions.CannotDeleteCourseException;
import com.example.demodocker.repositories.CourseRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CourseService.class})
@ExtendWith(SpringExtension.class)
public class CourseServiceTest {
    @MockBean
    private CourseRepository courseRepository;

    @Autowired
    private CourseService courseService;

    @Test
    public void testListAll() {
        ArrayList<Course> courseList = new ArrayList<Course>();
        when(this.courseRepository.findAll()).thenReturn(courseList);
        List<Course> actualListAllResult = this.courseService.listAll();
        assertSame(courseList, actualListAllResult);
        assertTrue(actualListAllResult.isEmpty());
        verify(this.courseRepository).findAll();
    }


    @Test
    public void testDelete() {
        Author author = new Author();
        author.setAuthorName("qbc");
        author.setAuthorImage("qbc");
        author.setId(1);
        author.setAuthorDescription("qbc");

        Interest interest = new Interest();
        interest.setId(1);
        interest.setInterestArea("qbc_area");

        Course course = new Course();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        course.setDiscountLastDate(Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant()));
        course.setAuthor(author);
        course.setShortDescription("qbcqbc");
        course.setDiscountPercentage(10.0f);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        course.setUpdatedTime(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
        course.setName("nameeeee");
        course.setImages(new HashSet<CourseImage>());
        course.setReviewCount(3);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        course.setCreatedTime(Date.from(atStartOfDayResult2.atZone(ZoneId.systemDefault()).toInstant()));
        course.setMainImage("mainimage");
        course.setId(123L);
        course.setFullDescription("qbcqbcbqbqbqbq");
        course.setCost(10.0f);
        course.setCategory(interest);
        course.setAverageRating(10.0f);
        Optional<Course> ofResult = Optional.<Course>of(course);
        doNothing().when(this.courseRepository).deleteById((Long) any());
        when(this.courseRepository.findById((Long) any())).thenReturn(ofResult);
        this.courseService.delete(123L);
        verify(this.courseRepository).deleteById((Long) any());
        verify(this.courseRepository).findById((Long) any());
    }

    @Test
    public void testDelete2() {
        doNothing().when(this.courseRepository).deleteById((Long) any());
        when(this.courseRepository.findById((Long) any())).thenReturn(Optional.<Course>empty());
        assertThrows(CannotDeleteCourseException.class, () -> this.courseService.delete(123L));
        verify(this.courseRepository).findById((Long) any());
    }


}

