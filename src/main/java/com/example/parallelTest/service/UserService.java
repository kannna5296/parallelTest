package com.example.parallelTest.service;

import com.example.parallelTest.entity.User;
import com.example.parallelTest.repository.UserRepository;
import org.joda.time.DateTime;
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
        return userRepository.findAll();
    }

    public void createUser(String name, String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        DateTime date = new DateTime();
        user.setCreatedAt(date);
        user.setUpdatedAt(date);
        userRepository.save(user);
    }
}
