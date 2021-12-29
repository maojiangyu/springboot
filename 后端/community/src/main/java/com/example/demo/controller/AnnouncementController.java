package com.example.demo.controller;

import com.example.demo.Response.AnnouncementResponse;
import com.example.demo.entity.Announcement;
import com.example.demo.entity.Community;
import com.example.demo.repository.CommunityRepository;
import com.example.demo.service.impl.AnnouncementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class AnnouncementController {

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private AnnouncementServiceImpl announcementService;


    @RequestMapping(method = RequestMethod.POST,value = "/api/addAnnouncement",produces = "application/json;charset=UTF-8")
    public ResponseEntity<Map<String,Object>> addAnnouncement(@RequestBody Map map){
        if (!map.get("name").equals("")) {
            int id = (int) map.get("CID");
            Community community = communityRepository.findById(id);
            Announcement announcement =new Announcement();
            announcement.setName(map.get("name").toString());
            announcement.setCreateTime(new Date());
            announcement.setModifyTime(new Date());
            announcement.setCommunity(community);
            announcement.setDescribeA(map.get("describeA").toString());
            announcementService.addAnnouncement(announcement);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(method = RequestMethod.POST,value = "/api/editAnnouncement",produces = "application/json;charset=UTF-8")
    public ResponseEntity<Map<String,Object>> editAnnouncement(@RequestBody Map map){
        int id = (int) map.get("CID");
        Community community = communityRepository.findById(id);
        Announcement announcement =new Announcement();
        announcement.setName((String) map.get("name"));
        announcement.setModifyTime(new Date());
        announcement.setCommunity(community);
        announcement.setDescribeA((String) map.get("describeA"));
        announcementService.addAnnouncement(announcement);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/api/deleteAnnouncement")
    public ResponseEntity<Map<String,Object>> deleteAnnouncement(@RequestBody Map map){
        long AID = Long.parseLong(map.get("id").toString());
        announcementService.deleteDyId(AID);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/api/community/{community_id}/announcement")
    public List<Announcement> listAnnouncement(@PathVariable("community_id") int community_id)throws Exception{
        if (community_id!=0){
            return announcementService.listByCategory(community_id);
        }else
            return null;
    }

    @GetMapping("/api/community/{community_id}/{name}/announcement")
    public List<Announcement> SearcherlistAnnouncement(@PathVariable("community_id") int community_id,@PathVariable("name")String name)throws Exception{
        if (community_id!=0){
            Community community = communityRepository.findById(community_id);
            return announcementService.listByName(name, community);
        }else
            return null;
    }
}
