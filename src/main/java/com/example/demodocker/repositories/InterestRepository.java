package com.example.demodocker.repositories;

import com.example.demodocker.entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestRepository extends JpaRepository<Interest, Integer> {

    @Query(value = "SELECT i from Interest i where i.interestArea = ?1", nativeQuery = true)
    Interest findByName(String interestArea);

}
