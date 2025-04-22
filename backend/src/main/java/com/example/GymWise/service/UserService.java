package com.example.GymWise.service;

import com.example.GymWise.entity.Split;
import com.example.GymWise.entity.User;
import com.example.GymWise.repository.SplitRepository;
import com.example.GymWise.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final SplitRepository splitRepository;

    public UserService(UserRepository userRepository, EmailService emailService, SplitRepository splitRepository) {
        this.userRepository = userRepository;
        this.splitRepository = splitRepository;
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

    public void saveSplitForUser(String userEmail, Long splitId) {
        User user = userRepository.findByEmailWithSplits(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Split split = splitRepository.findById(splitId)
                .orElseThrow(() -> new RuntimeException("Split not found"));

        if (!user.getSavedSplits().contains(split)) {
            user.getSavedSplits().add(split);
            userRepository.save(user);
        }
    }

    public void removeSplitForUser(String userEmail, Long splitId) {
        User user = userRepository.findByEmailWithSplits(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.getSavedSplits().removeIf(split -> split.getId().equals(splitId));
        userRepository.save(user);
    }

    public List<Split> getSavedSplits(String userEmail) {
        User user = userRepository.findByEmailWithSplits(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getSavedSplits();
    }
}
