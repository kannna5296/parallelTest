package com.example.parallelTest.controller;

import com.example.parallelTest.entity.User;
import com.example.parallelTest.request.CreateUserRequest;
import com.example.parallelTest.request.UpdateUserRequest;
import com.example.parallelTest.response.EmptyResponse;
import com.example.parallelTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

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
    public ResponseEntity<EmptyResponse> createUser(@RequestBody CreateUserRequest req) {
        userService.createUser(req.getName(),req.getPassword());
        return ResponseEntity.ok(new EmptyResponse());
    }

    //UPDATE
    @PutMapping("/user")
    public ResponseEntity<EmptyResponse> createUser(@RequestBody UpdateUserRequest req) {
        userService.updateUser(req.getId(), req.getNewName());
        return ResponseEntity.ok(new EmptyResponse());
    }

    //DELETE
    @DeleteMapping("/user")
    public ResponseEntity<EmptyResponse> deleteUser(@RequestBody UpdateUserRequest req) {
        userService.deleteUser(req.getId());
        return ResponseEntity.ok(new EmptyResponse());
    }
}
