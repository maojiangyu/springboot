package com.example.demo.service;

import com.example.demo.entity.Community;

import java.util.List;

public interface CommunityService {
    public List<Community> getCommunityList();
    public Community findCommunityById(long id);
}
