package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.impl.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserServiceImp userServiceImp;

    /*
    post(api/login)  public UserCheck xxxxxxxxxxxxx
    接收pwd,ssid
    getallUser
    check
    new userCheck -----> user1
    user1.set(ssid)
    user1.set(id);
    user1.isUser()//有 true 没有 false
    return user1


     */


}
