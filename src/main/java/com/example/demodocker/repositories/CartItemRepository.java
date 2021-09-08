package com.example.demodocker.repositories;

import com.example.demodocker.entity.CartItem;
import com.example.demodocker.entity.Course;
import com.example.demodocker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    CartItem findByCourseAndUser(Course course, User user);

    List<CartItem> findByUser(User user);

    void deleteAllByUser(User user);
}
