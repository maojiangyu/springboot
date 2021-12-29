package com.example.demo.repository;

import com.example.demo.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community,Long> {
    Community findById(long id);
    //Community deleteBy(long id);
}
