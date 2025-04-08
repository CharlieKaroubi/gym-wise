package com.example.GymWise.service;

import com.example.GymWise.entity.User;
import com.example.GymWise.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public List<String> allUsernames() {
        List<String> usernames = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            if (user.isEnabled()){
                usernames.add(user.getTrueUsername());
            }
        }
        return usernames;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
