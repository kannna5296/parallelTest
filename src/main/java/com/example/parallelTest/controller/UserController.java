package com.example.parallelTest.controller;

import com.example.parallelTest.entity.User;
import com.example.parallelTest.repository.UserRepository;
import com.example.parallelTest.request.createUserRequest;
import com.example.parallelTest.request.updateUserRequest;
import com.example.parallelTest.response.EmptyResponse;
import com.example.parallelTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    //コンストラクタインジェクション
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //READ
    @GetMapping("/user")
    public ResponseEntity<List<User>> getUser() {
        return ResponseEntity.ok(userService.getUsers());
    }

    //CREATE
    @PostMapping("/user")
    public ResponseEntity<EmptyResponse> createUser(createUserRequest req) {
        userService.createUser(req.getName(),req.getPassword());
        return ResponseEntity.ok(new EmptyResponse());
    }

    //UPDATE
    @PutMapping("/user")
    public ResponseEntity<EmptyResponse> createUser(updateUserRequest req) {
        userService.updateUser(req.getId(), req.getNewName());
        return ResponseEntity.ok(new EmptyResponse());
    }

    //DELETE
    @DeleteMapping("/user")
    public ResponseEntity<EmptyResponse> delteUser(updateUserRequest req) {
        userService.deleteUser(req.getId());
        return ResponseEntity.ok(new EmptyResponse());
    }
}
