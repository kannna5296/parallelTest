package com.example.parallelTest.entity;

import com.example.parallelTest.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.sql.Timestamp;

//ドメインサービス
@Service
public class UserService {

    private final UserRepository userRepository;

    //コンストラクタインジェクション
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // すでに居るユーザを検知
    public boolean exists(String name) {
        User dupUser = userRepository.findByName(name);
        return dupUser != null;
    }
}
