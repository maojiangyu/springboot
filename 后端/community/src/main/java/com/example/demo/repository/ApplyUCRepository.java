package com.example.demo.repository;

import com.example.demo.entity.ApplyUC;
import com.example.demo.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyUCRepository extends JpaRepository<ApplyUC,Long> {
    List<ApplyUC> findByCommunity(Community community);
    ApplyUC findById(long id);
}
