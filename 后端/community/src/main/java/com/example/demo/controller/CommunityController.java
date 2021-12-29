package com.example.demo.controller;

import com.example.demo.Result.CommunityResult;
import com.example.demo.entity.*;
import com.example.demo.repository.CommunityRepository;
import com.example.demo.repository.UCRepsitory;
import com.example.demo.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class CommunityController {
    @Autowired
    private CommunityServiceImlp communityServiceImlp;

    @Autowired
    private UCServiceImp ucServiceImp;

    @Autowired
    private UserServiceImp userServiceImp;

    @Autowired
    private ApplyUCService applyUCService;

    @Autowired
    private applyRecordServiceImp applyRecordServiceImp1;

    @GetMapping("/api/community/{user_id}/community")
    public List<UC> listAnnouncement(@PathVariable("user_id") int user_id)throws Exception{
        if (user_id!=0){
            return ucServiceImp.listByUser(user_id);
        }
        else
            return null;
    }

    @GetMapping("/api/community/{community_id}/getCInfo")
    public Community getCommunity(@PathVariable("community_id") int community_id){
        if (community_id!=0){
            return communityServiceImlp.findCommunityById(community_id);
        }else
            return null;
    }

    @GetMapping("/api/communityAll")
    public List<Community> listCommunity(){
        return communityServiceImlp.findAll();
    }

    @GetMapping("/api/community/{community_id}/apply")
    public List<ApplyUC> listApplyUC(@PathVariable("community_id") int community_id){
        return applyUCService.findByCommunity(community_id);
    }

    @GetMapping("/api/community/{community_id}/getMember")
    public List<showMember> listMember(@PathVariable("community_id") int community_id){
        List<UC> list= ucServiceImp.listByCommunity(community_id);
        List<showMember> list1 = new ArrayList<>();
        for (int i = 0; i < list.size();i ++){
            showMember s = new showMember();
            s.setCreateTime(list.get(i).getCreateTime());
            s.setSName(list.get(i).getUser().getSName());
            s.setId(list.get(i).getUser().getID());
            s.setUCID(list.get(i).getID());
            list1.add(s);
        }
        return list1;
    }

    @PostMapping("/api/deleteApply")
    public ResponseEntity<Map<String,Object>> deleteAnnouncement(@RequestBody Map map){
        long AID = Long.parseLong(map.get("id").toString());
        ApplyUC applyUC = applyUCService.findById(AID);
        applyRecord applyRecord1 = new applyRecord();
        applyRecord1.setCommunity(applyUC.getCommunity());
        applyRecord1.setUser(applyUC.getUser());
        applyRecord1.setCreateTime(new Date());
        applyRecord1.setPass(false);
        applyRecordServiceImp1.addRecord(applyRecord1);
        applyUCService.deleteApplyUC(AID);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/api/passApply",produces = "application/json;charset=UTF-8")
    public ResponseEntity<Map<String,Object>> passApply(@RequestBody Map map){
        User user = userServiceImp.findById(Integer.parseInt(map.get("sid").toString()));
        Community community = communityServiceImlp.findCommunityById(Integer.parseInt(map.get("cid").toString()));
        long AID = Long.parseLong(map.get("id").toString());
        ApplyUC applyUC = applyUCService.findById(AID);
        applyRecord applyRecord1 = new applyRecord();
        applyRecord1.setCommunity(applyUC.getCommunity());
        applyRecord1.setUser(applyUC.getUser());
        applyRecord1.setCreateTime(new Date());
        applyRecord1.setPass(true);
        applyRecordServiceImp1.addRecord(applyRecord1);
        UC uc = new UC();
        uc.setCommunity(community);
        uc.setUser(user);
        uc.setCreateTime(new Date());
        ucServiceImp.addUC(uc);
        applyUCService.deleteApplyUC(AID);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/api/deleteMember")
    public ResponseEntity<Map<String,Object>> deleteMember(@RequestBody Map map){
        long AID = Long.parseLong(map.get("id").toString());
        ucServiceImp.delete(AID);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/api/changeLeader",produces = "application/json;charset=UTF-8")
    public ResponseEntity<Map<String,Object>> changeLeader(@RequestBody Map map){
        long myId = Long.parseLong(map.get("myID").toString());
        long id = Long.parseLong(map.get("id").toString());
        UC myUC = ucServiceImp.findUCById(myId);
        UC uc = ucServiceImp.findUCById(id);
        uc.setLeader(true);
        myUC.setLeader(false);
        ucServiceImp.addUC(uc);
        ucServiceImp.addUC(myUC);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/api/community/{community_id}/getApplyRecord")
    public List<applyRecord> listApplyRecord(@PathVariable("community_id") int community_id){
        Community community = communityServiceImlp.findCommunityById(community_id);
        return applyRecordServiceImp1.findAllByCommunity(community);
    }

    @GetMapping("/api/community/{community_id}/{name}/searcherRecord")
    public List<applyRecord> SearcherListRecord(@PathVariable("community_id") int community_id,@PathVariable("name")String name)throws Exception {
        List<User> list1 = userServiceImp.findBySName(name);
        Community community = communityServiceImlp.findCommunityById(community_id);
        List<applyRecord> list2 = new ArrayList<>();
        for (int i = 0 ; i < list1.size();i++){
            List<applyRecord> list3 = applyRecordServiceImp1.listByUserAndCommunity(list1.get(i), community);
            list2.addAll(list3);
        }
        return list2;
    }
}
