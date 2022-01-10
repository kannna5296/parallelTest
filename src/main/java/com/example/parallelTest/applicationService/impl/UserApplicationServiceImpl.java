package com.example.parallelTest.applicationService.impl;

import com.example.parallelTest.applicationService.UserApplicationService;
import com.example.parallelTest.entity.User;
import com.example.parallelTest.entity.UserService;
import com.example.parallelTest.exception.NotValidException;
import com.example.parallelTest.repository.UserRepository;
import java.sql.Timestamp;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

  private final UserRepository userRepository;
  private final UserService userService;

  // コンストラクタインジェクション
  @Autowired
  public UserApplicationServiceImpl(UserRepository userRepository, UserService userService) {
    this.userRepository = userRepository;
    this.userService = userService;
  }

  public List<User> getUsers() {
    List<User> users = userRepository.findAll();
    return users;
  }

  public void createUser(String name, String password) throws Exception {

    // 重複チェック
    if (userService.exists(name)) throw new NotValidException();

    User user = new User();
    user.setName(name);
    user.setPassword(password);
    DateTime now = new DateTime();
    user.setCreatedAt(now);
    user.setUpdatedAt(now);
    userRepository.save(user);
  }

  public void updateUser(Integer id, String newName) throws Exception {

    // 重複チェック
    if (userService.exists(newName)) throw new NotValidException();

    User user = userRepository.findById(id).get();
    user.setName(newName);
    DateTime now = new DateTime();
    user.setUpdatedAt(now);
    userRepository.save(user);
  }

  public void deleteUser(Integer id) {
    userRepository.deleteById(id);
  }
}
