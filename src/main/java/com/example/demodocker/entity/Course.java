package com.example.demodocker.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 256, nullable = false)
    private String name;

    @Column(name = "Short_description", nullable = false, length = 256)
    private String shortDescription;

    @Column(length = 4096, nullable = false, name="full_description")
    private String fullDescription;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "main_image")
    private String mainImage;

    @Column(name="updated_time")
    private Date updatedTime;

    private float cost;

    @Column(name="discount_percentage")
    private float discountPercentage;

    @Column(name="discount_last_date")
    private Date discountLastDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Interest category;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(name = "review_count", nullable = true)
    private int reviewCount;

    @Column(name = "average_rating", nullable = true)
    private float averageRating;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<CourseImage> images = new HashSet<>();

    private String url;

    public Course(Long id) {
        this.id = id;
    }

    public Course(String name, String shortDescription, String fullDescription, Date createdTime, Date updatedTime, float cost, float discountPercentage, Date discountLastDate, Interest category, Author author) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.cost = cost;
        this.discountPercentage = discountPercentage;
        this.discountLastDate = discountLastDate;
        this.category = category;
        this.author = author;
        this.url = url;
    }

    public void addExtraImage(String imageName){
        this.images.add(new CourseImage(imageName, this));
    }
}
