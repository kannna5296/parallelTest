package com.example.parallelTest.controller;

import com.example.parallelTest.applicationService.UserApplicationService;
import com.example.parallelTest.entity.User;
import com.example.parallelTest.request.CreateUserRequest;
import com.example.parallelTest.request.UpdateUserRequest;
import com.example.parallelTest.response.CreateUserResponse;
import com.example.parallelTest.response.EmptyResponse;
import java.util.List;

import com.example.parallelTest.response.ReadUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

  private final UserApplicationService userApplicationService;

  @Autowired
  public UserController(UserApplicationService userApplicationService) {
    this.userApplicationService = userApplicationService;
  }

  // READ
  @GetMapping("/")
  public ResponseEntity<ReadUserResponse> getUser() {
    List<User> users = userApplicationService.getUsers();
    return ResponseEntity.ok(new ReadUserResponse(users));
  }

  // CREATE
  @PostMapping("/")
  public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest req)
      throws Exception {
    User user = userApplicationService.createUser(req.getName(), req.getPassword());
    return ResponseEntity.ok(new CreateUserResponse(user));
  }

  // UPDATE
  @PutMapping("/")
  public ResponseEntity<EmptyResponse> createUser(@RequestBody UpdateUserRequest req)
      throws Exception {
    userApplicationService.updateUser(req.getId(), req.getNewName());
    return ResponseEntity.ok(new EmptyResponse());
  }

  // DELETE
  @DeleteMapping("/")
  public ResponseEntity<EmptyResponse> deleteUser(@RequestBody UpdateUserRequest req) {
    userApplicationService.deleteUser(req.getId());
    return ResponseEntity.ok(new EmptyResponse());
  }
}
