package com.example.parallelTest.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Access;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class createUserRequest {

    private String name;

    private String password;
}
