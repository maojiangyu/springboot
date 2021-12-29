package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public User findById(long id) {
        return userRepository.findById(id);
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public List<User> findAll(){return userRepository.findAll();}

    public List<User> findBySName(String name){return userRepository.findAllBySNameStartingWith(name);}
}
