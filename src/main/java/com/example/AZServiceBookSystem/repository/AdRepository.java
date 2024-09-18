package com.example.AZServiceBookSystem.repository;

import com.example.AZServiceBookSystem.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad,Long> {

    List<Ad> findAdByUserId(Long userId);
    List<Ad> findAllByServiceNameContaining(String name);
}
