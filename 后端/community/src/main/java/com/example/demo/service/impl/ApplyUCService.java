package com.example.demo.service.impl;

import com.example.demo.entity.ApplyUC;
import com.example.demo.entity.Community;
import com.example.demo.repository.ApplyUCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplyUCService {
    @Autowired
    private ApplyUCRepository applyUCRepository;

    @Autowired
    private CommunityServiceImlp communityServiceImlp;

    public List<ApplyUC> findByCommunity(int community_id){
        Community community = communityServiceImlp.findCommunityById(community_id);
        return applyUCRepository.findByCommunity(community);
    }

    public void addApplyUC(ApplyUC applyUC){
        applyUCRepository.save(applyUC);
    }

    public void deleteApplyUC(long id){applyUCRepository.deleteById(id);}

    public ApplyUC findById(long id){return applyUCRepository.findById(id);}
}
