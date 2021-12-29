package com.example.demo.repository;

import com.example.demo.entity.Community;
import com.example.demo.entity.UC;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UCRepsitory extends JpaRepository<UC,Long> {
    List<UC> findByUser(User user);
    List<UC> findByCommunity(Community community);
    UC findById(long id);
}
