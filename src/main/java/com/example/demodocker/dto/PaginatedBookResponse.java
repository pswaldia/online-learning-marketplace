package com.example.demodocker.dto;

import com.example.demodocker.entity.Course;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PaginatedBookResponse {
    private List<Course> courseList;
    private Long numberOfItems;
    private Integer numberOfPages;
}
