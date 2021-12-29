package com.example.demo.repository;

import com.example.demo.entity.Announcement;
import com.example.demo.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement,Long> {
    Announcement findById(long id);
    //Announcement deleteById(long id);

    List<Announcement> findAllByCommunity(Community community);

    List<Announcement> findAllByNameStartingWithAndCommunity(String name,Community community);
}
