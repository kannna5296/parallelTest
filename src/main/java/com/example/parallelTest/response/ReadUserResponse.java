package com.example.parallelTest.response;

import com.example.parallelTest.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class ReadUserResponse {

    public ReadUserResponse(List<User> users) {
        this.users = users;
    }

    private List<User> users;
}
