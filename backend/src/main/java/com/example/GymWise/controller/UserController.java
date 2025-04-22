package com.example.GymWise.controller;

import com.example.GymWise.dto.LoginUserDto;
import com.example.GymWise.dto.UserProfileDto;
import com.example.GymWise.entity.User;
import com.example.GymWise.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {this.userService = userService;}


    @GetMapping("/profile")
    public ResponseEntity<UserProfileDto> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            String email = userDetails.getUsername();
            User user = userService.findByEmail(email);
            return ResponseEntity.ok(new UserProfileDto(user.getTrueUsername(), email, userService.getSavedSplits(email)));
        }
        return ResponseEntity.status(401).build();
    }

    @GetMapping
    public ResponseEntity<List<String>> allUsernames() {
        List<String> usernames = userService.allUsernames();
        return ResponseEntity.ok(usernames);
    }


    @PostMapping("/save-split/{splitId}")
    public ResponseEntity<?> saveSplit(@PathVariable Long splitId, @RequestHeader("Authorization") String authHeader) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        userService.saveSplitForUser(email, splitId);
        return ResponseEntity.ok("Split saved successfully");
    }
}
