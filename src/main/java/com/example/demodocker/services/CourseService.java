package com.example.demodocker.services;

import com.example.demodocker.dto.PaginatedBookResponse;
import com.example.demodocker.entity.Course;
import com.example.demodocker.exceptions.CannotDeleteCourseException;
import com.example.demodocker.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> listAll(){
        return courseRepository.findAll();
    }

    public PaginatedBookResponse readCourse(Pageable pageable){
        Page<Course> courses = courseRepository.findAll(pageable);
        return PaginatedBookResponse.builder()
                .numberOfItems(courses.getTotalElements())
                .numberOfPages(courses.getTotalPages())
                .courseList(courses.getContent())
                .build();
    }

    public PaginatedBookResponse filterCourse(String keyword, Pageable pageable){

        Page<Course> courses = courseRepository.findAll(keyword, pageable);

        return PaginatedBookResponse.builder()
                .numberOfItems(courses.getTotalElements())
                .numberOfPages(courses.getTotalPages())
                .courseList(courses.getContent())
                .build();
    }

    public void delete(Long id) {
        Course course = courseRepository.findById(id).orElseThrow( () -> new CannotDeleteCourseException("Course with id: "+id+" do not exist and hence cannot be deleted"));
        courseRepository.deleteById(id);
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public Course update(Course course) {
        return courseRepository.save(course);
    }

    public Course getById(Integer id) {
        return courseRepository.findById(Long.valueOf(id)).get();
    }

}
