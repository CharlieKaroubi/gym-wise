package com.example.GymWise.controller;

import com.example.GymWise.dto.LoginUserDto;
import com.example.GymWise.dto.UserProfileDto;
import com.example.GymWise.entity.User;
import com.example.GymWise.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {this.userService = userService;}


    @GetMapping("/me")
    public ResponseEntity<UserProfileDto> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            String email = userDetails.getUsername();
            User user = userService.findByEmail(email);
            return ResponseEntity.ok(new UserProfileDto(user.getTrueUsername(), user.getEmail()));
        }
        return ResponseEntity.status(401).build();
    }

    @GetMapping
    public ResponseEntity<List<String>> allUsernames() {
        List<String> usernames = userService.allUsernames();
        return ResponseEntity.ok(usernames);
    }
}
