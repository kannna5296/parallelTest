package com.example.parallelTest.controller;

import com.example.parallelTest.entity.User;
import com.example.parallelTest.request.CreateUserRequest;
import com.example.parallelTest.request.UpdateUserRequest;
import com.example.parallelTest.response.EmptyResponse;
import com.example.parallelTest.applicationService.UserApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserApplicationService userApplicationService;

    @Autowired
    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    //READ
    @GetMapping("/user")
    public ResponseEntity<List<User>> getUser() {
        return ResponseEntity.ok(userApplicationService.getUsers());
    }

    //CREATE
    @PostMapping("/user")
    public ResponseEntity<EmptyResponse> createUser(@RequestBody CreateUserRequest req) throws Exception{
        userApplicationService.createUser(req.getName(),req.getPassword());
        return ResponseEntity.ok(new EmptyResponse());
    }

    //UPDATE
    @PutMapping("/user")
    public ResponseEntity<EmptyResponse> createUser(@RequestBody UpdateUserRequest req) throws Exception{
        userApplicationService.updateUser(req.getId(), req.getNewName());
        return ResponseEntity.ok(new EmptyResponse());
    }

    //DELETE
    @DeleteMapping("/user")
    public ResponseEntity<EmptyResponse> deleteUser(@RequestBody UpdateUserRequest req) {
        userApplicationService.deleteUser(req.getId());
        return ResponseEntity.ok(new EmptyResponse());
    }
}
