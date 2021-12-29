package com.example.demo.repository;

import com.example.demo.entity.Announcement;
import com.example.demo.entity.Community;
import com.example.demo.entity.User;
import com.example.demo.entity.applyRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface applyRecordRepository extends JpaRepository<applyRecord,Long> {
    applyRecord findById(long id);
    List<applyRecord> findAllByUser(User user);
    List<applyRecord> findAllByCommunity(Community community);
    List<applyRecord> findAllByUserAndCommunity(User user,Community community);
}
