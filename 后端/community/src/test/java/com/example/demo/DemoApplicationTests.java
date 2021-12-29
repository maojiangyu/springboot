package com.example.demo;

import com.example.demo.entity.*;
import com.example.demo.repository.AnnouncementRepository;
import com.example.demo.repository.CommunityRepository;
import com.example.demo.service.impl.AnnouncementServiceImpl;
import com.example.demo.service.impl.ApplyUCService;
import com.example.demo.service.impl.UCServiceImp;
import com.example.demo.service.impl.UserServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class DemoApplicationTests {
	@Autowired
	private CommunityRepository communityRepository;
	@Autowired
	private AnnouncementServiceImpl announcementService;

	@Autowired
	private UserServiceImp userServiceImp;

	@Autowired
	private UCServiceImp ucServiceImp;

	@Autowired
	private ApplyUCService applyUCService;

	@Test
	public void add(){
		Community community1 = new Community();
		community1.setName("动漫社");
		community1.setDescribeC("动漫爱好者");
		community1.setTeacher("111");
		community1.setCreateTime(new Date());
		communityRepository.save(community1);
		Community community2 = new Community();
		community2.setName("电影社");
		community2.setDescribeC("动漫爱好者");
		community2.setTeacher("111");
		community2.setCreateTime(new Date());
		communityRepository.save(community2);
		User user1 = new User();
		user1.setSSID("1");
		user1.setPwd("1");
		user1.setSName("mjy1");
		userServiceImp.addUser(user1);
		User user2 = new User();
		user2.setSSID("2");
		user2.setPwd("2");
		user2.setSName("mjy2");
		userServiceImp.addUser(user2);
		UC uc1 = new UC();
		uc1.setCommunity(community1);
		uc1.setUser(user1);
		uc1.setLeader(true);
		uc1.setCreateTime(new Date());
		ucServiceImp.addUC(uc1);
		UC uc2 = new UC();
		uc2.setCommunity(community2);
		uc2.setUser(user1);
		uc2.setLeader(false);
		uc2.setCreateTime(new Date());
		ucServiceImp.addUC(uc2);
		UC uc3 = new UC();
		uc3.setCommunity(community2);
		uc3.setUser(user2);
		uc3.setLeader(true);
		uc3.setCreateTime(new Date());
		ucServiceImp.addUC(uc3);
	}

	@Test
	public void add2(){
		Community community1 = communityRepository.findById(1);
		Community community2 = communityRepository.findById(2);
		User user1 = new User();
		user1.setSSID("3");
		user1.setPwd("3");
		user1.setSName("mjy3");
		userServiceImp.addUser(user1);
		User user2 = new User();
		user2.setSSID("4");
		user2.setPwd("4");
		user2.setSName("mjy4");
		UC uc1 = new UC();
		uc1.setCommunity(community1);
		uc1.setUser(user1);
		uc1.setLeader(false);
		uc1.setCreateTime(new Date());
		ucServiceImp.addUC(uc1);
		UC uc2 = new UC();
		uc2.setCommunity(community2);
		uc2.setUser(user1);
		uc2.setLeader(false);
		uc2.setCreateTime(new Date());
		ucServiceImp.addUC(uc2);
	}

	@Test
	public void addApply(){
		Community community1 = communityRepository.findById(1);
		Community community2 = communityRepository.findById(2);
		User user = userServiceImp.findById(4);
		User user1 = userServiceImp.findById(5);
		ApplyUC applyUC = new ApplyUC();
		applyUC.setCommunity(community1);
		applyUC.setUser(user);
		applyUC.setCreateTime(new Date());
		applyUCService.addApplyUC(applyUC);
		ApplyUC applyUC2 = new ApplyUC();
		applyUC2.setCommunity(community1);
		applyUC2.setUser(user1);
		applyUC2.setCreateTime(new Date());
		applyUCService.addApplyUC(applyUC2);
		ApplyUC applyUC3 = new ApplyUC();
		applyUC3.setCommunity(community2);
		applyUC3.setUser(user);
		applyUC3.setCreateTime(new Date());
		applyUCService.addApplyUC(applyUC3);
	}

	@Test
	public void search(){
		User user = userServiceImp.findById(1);
		List<UC> list1 = ucServiceImp.listByUser(1);
		System.out.println(list1.size());
		for (UC community:list1){
			System.out.println(community.toString());
		}
	}


	@Test
	void contextLoads() {
	}

}
