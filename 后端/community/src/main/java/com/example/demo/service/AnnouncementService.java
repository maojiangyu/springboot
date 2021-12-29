package com.example.demo.service;

import com.example.demo.entity.Announcement;

import java.util.List;

public interface AnnouncementService {
    public List<Announcement> getAnnouncementList();
    public Announcement findAnnouncementById(long id);
}
