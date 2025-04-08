package com.example.GymWise.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileDto {
    private String username;
    private String email;

    public UserProfileDto(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public UserProfileDto() {}
}