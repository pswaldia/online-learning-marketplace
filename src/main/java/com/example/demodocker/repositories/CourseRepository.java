package com.example.demodocker.repositories;

import com.example.demodocker.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query(value="SELECT FROM Course c WHERE c.name LIKE %?1% "+
            "OR c.shortDescription LIKE %?1% "+
            "OR c.fullDescription LIKE %?1% "+
            "OR c.author.authorName LIKE %?1% "+
            "OR c.category.interestArea LIKE %?1%" , nativeQuery=true)
    public Page<Course> findAll(String keyword,Pageable pageable);

}
