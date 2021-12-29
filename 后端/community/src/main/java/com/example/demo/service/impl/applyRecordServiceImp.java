package com.example.demo.service.impl;

import com.example.demo.entity.Community;
import com.example.demo.entity.User;
import com.example.demo.entity.applyRecord;
import com.example.demo.repository.applyRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class applyRecordServiceImp {
    @Autowired
    private applyRecordRepository applyRecordRepository1;

    public List<applyRecord> findAllByCommunity(Community community){
        return applyRecordRepository1.findAllByCommunity(community);
    }

    public List<applyRecord> findAllByUser(User user){
        return applyRecordRepository1.findAllByUser(user);
    }

    public void addRecord(applyRecord applyRecord1){
        applyRecordRepository1.save(applyRecord1);
    }

    public applyRecord findById(long id){
        return applyRecordRepository1.findById(id);
    }

    //public List<applyRecord> findByUserName(String name,int cid){return applyRecordRepository1.findByUserNameLike(name,cid);}

    public List<applyRecord> listByUserAndCommunity(User user,Community community){return applyRecordRepository1.findAllByUserAndCommunity(user, community);}
}
