package com.example.demodocker.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="order_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private float Cost;
    private float discountPercentage;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public OrderDetails(float cost, float discountPercentage, Course course) {
        Cost = cost;
        this.discountPercentage = discountPercentage;
        this.course = course;
    }
}
