package com.example.demo.service.impl;

import com.example.demo.entity.Announcement;
import com.example.demo.entity.Community;
import com.example.demo.entity.User;
import com.example.demo.repository.AnnouncementRepository;
import com.example.demo.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private CommunityServiceImlp communityServiceImlp;

    @Override
    public List<Announcement> getAnnouncementList() {
        return announcementRepository.findAll();
    }

    public void addAnnouncement(Announcement announcement){
        announcementRepository.save(announcement);
    }

    @Override
    public Announcement findAnnouncementById(long id) {
        return announcementRepository.findById(id);
    }

    public List<Announcement> listByCategory(int community_id) {
        Community community = communityServiceImlp.findCommunityById(community_id);
        return announcementRepository.findAllByCommunity(community);
    }

    public List<Announcement> listByName(String name,Community community){return announcementRepository.findAllByNameStartingWithAndCommunity(name,community);}

    public void deleteDyId(long id){
        announcementRepository.deleteById(id);
    }

}
