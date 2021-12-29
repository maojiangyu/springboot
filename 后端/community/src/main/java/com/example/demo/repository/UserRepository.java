package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    User findById(long id);
    List<User> findAll();
    List<User> findAllBySNameStartingWith(String name);
}
