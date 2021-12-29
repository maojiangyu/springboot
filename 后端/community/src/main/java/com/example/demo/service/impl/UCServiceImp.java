package com.example.demo.service.impl;

import com.example.demo.entity.Announcement;
import com.example.demo.entity.Community;
import com.example.demo.entity.UC;
import com.example.demo.entity.User;
import com.example.demo.repository.UCRepsitory;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UCService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UCServiceImp implements UCService {
    @Autowired
    private UCRepsitory ucRepsitory;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommunityServiceImlp communityServiceImlp;

    public List<UC> listByUser(int community_id) {
        User user = userRepository.findById(community_id);
        return ucRepsitory.findByUser(user);
    }

    public List<UC> listByCommunity(int community_id){
        Community community = communityServiceImlp.findCommunityById(community_id);
        return ucRepsitory.findByCommunity(community);
    }

    public void addUC(UC uc){
        ucRepsitory.save(uc);
    }

    public void delete(long id){
        ucRepsitory.deleteById(id);
    }

    public UC findUCById(long id){
        return ucRepsitory.findById(id);
    }
}
