package com.example.parallelTest.response;

import com.example.parallelTest.entity.User;
import lombok.Data;

@Data
public class CreateUserResponse {

    public CreateUserResponse(User user) {
        this.name = user.getName();
        this.password = user.getPassword();
        this.createdAt = user.getCreatedAt().toString();
        this.updatedAt = user.getUpdatedAt().toString();
    }

    private String name;

    private String password;

    private String createdAt;

    private String updatedAt;
}
