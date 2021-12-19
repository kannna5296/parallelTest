package com.example.parallelTest.controller;

import com.example.parallelTest.entity.User;
import com.example.parallelTest.repository.UserRepository;
import com.example.parallelTest.request.createUserRequest;
import com.example.parallelTest.response.EmptyResponse;
import com.example.parallelTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    //コンストラクタインジェクション
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getUser() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping("/user")
    public ResponseEntity<EmptyResponse> createUser(createUserRequest req) {
        userService.createUser(req.getName(),req.getPassword());
        return ResponseEntity.ok(new EmptyResponse());
    }
}
