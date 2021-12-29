package com.example.demo.service.impl;

import com.example.demo.entity.Community;
import com.example.demo.repository.CommunityRepository;
import com.example.demo.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommunityServiceImlp implements CommunityService {
    @Autowired
    private CommunityRepository communityRepository;

    @Override
    public List<Community> getCommunityList() {
        return communityRepository.findAll();
    }

    @Override
    public Community findCommunityById(long id) {
        return communityRepository.findById(id);
    }

    public List<Community> findAll(){return communityRepository.findAll();}
}
