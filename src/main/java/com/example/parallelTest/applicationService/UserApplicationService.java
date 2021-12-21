package com.example.parallelTest.applicationService;

import com.example.parallelTest.entity.User;

import org.springframework.stereotype.Service;


import java.util.List;

//アプリケーションサービス
@Service
public interface UserApplicationService {

    public List<User> getUsers();

    public void createUser(String name, String password) throws Exception;

    public void updateUser(Integer id, String newName);

    public void deleteUser(Integer id);
}
