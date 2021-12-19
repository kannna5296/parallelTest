package com.example.parallelTest.service;

import com.example.parallelTest.entity.User;
import com.example.parallelTest.repository.UserRepository;

import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    //コンストラクタインジェクション
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public void createUser(String name, String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        userRepository.save(user);
    }

    public void updateUser(Integer id, String newName) {
        User user = userRepository.findById(id).get();
        user.setName(newName);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        user.setUpdatedAt(now);
        userRepository.save(user);
    }
}
