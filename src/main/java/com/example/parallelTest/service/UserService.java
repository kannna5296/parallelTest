package com.example.parallelTest.service;

import com.example.parallelTest.entity.User;
import com.example.parallelTest.repository.UserRepository;

import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface UserService {

    public List<User> getUsers();

    public void createUser(String name, String password);

    public void updateUser(Integer id, String newName);

    public void deleteUser(Integer id);
}
