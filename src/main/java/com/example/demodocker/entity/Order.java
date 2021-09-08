package com.example.demodocker.entity;


import com.example.demodocker.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 128, unique = true, nullable = false)
    private String email;

    @Column(name = "first_name", length = 45, nullable = false)
    private String firstName;

    @Column(name="last_name", length = 45, nullable = false)
    private String lastName;

    @Enumerated(
            EnumType.STRING
    )
    private PaymentMethod paymentMethod;
    private Date orderTime;
    private float orderCost;
    private float Tax;
    private float total;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    private Set<OrderDetails> orderDetails = new HashSet<>();
}
