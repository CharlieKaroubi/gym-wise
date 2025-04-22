package com.example.GymWise.dto;

import com.example.GymWise.entity.Split;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserProfileDto {
    private String username;
    private String email;
    private List<Split> savedSplits;

    public UserProfileDto(String username, String email, List<Split> savedSplits) {
        this.username = username;
        this.email = email;
        this.savedSplits = savedSplits;
    }

    public UserProfileDto() {}
}