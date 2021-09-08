package com.example.demodocker.repositories;

import com.example.demodocker.entity.Course;
import com.example.demodocker.entity.Reviews;
import com.example.demodocker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Reviews, Long> {

    public Reviews findByUserAndCourse(User user, Course course);

    List<Reviews> findByUser(User user);
}

