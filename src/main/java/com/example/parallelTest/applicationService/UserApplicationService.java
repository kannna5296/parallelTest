package com.example.parallelTest.applicationService;

import com.example.parallelTest.entity.User;
import java.util.List;
import org.springframework.stereotype.Service;

// アプリケーションサービス
public interface UserApplicationService {

  public List<User> getUsers();

  public User createUser(String name, String password) throws Exception;

  public void updateUser(Integer id, String newName) throws Exception;

  public void deleteUser(Integer id);
}
