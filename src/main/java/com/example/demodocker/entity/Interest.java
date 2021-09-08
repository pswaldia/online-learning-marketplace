package com.example.demodocker.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="interest")
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Interest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interest_id")
    private Integer id;

    @Column(name = "interest_area", nullable = false)
    private String interestArea;

    public Interest(String interestArea) {
        this.interestArea = interestArea;
    }

    public Interest(Integer id) {
        this.id = id;
    }
}
