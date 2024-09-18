package com.example.AZServiceBookSystem.repository;

import com.example.AZServiceBookSystem.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findAllByAdId(Long id);
}
