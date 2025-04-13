package com.example.GymWise.controller;
import com.example.GymWise.dto.CreateSplitDto;
import com.example.GymWise.entity.Exercise;
import com.example.GymWise.entity.Split;
import com.example.GymWise.service.ExerciseService;
import com.example.GymWise.service.SplitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/splits")
public class SplitController {

    private final SplitService splitService;

    @Autowired
    public SplitController(SplitService splitService) {
        this.splitService = splitService;
    }

    @PostMapping
    public ResponseEntity<?> createSplit(@RequestBody CreateSplitDto createSplitDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            String email = userDetails.getUsername();
            try {
                Split createdSplit = splitService.createSplit(createSplitDto, email);
                return ResponseEntity.status(HttpStatus.CREATED).body(createdSplit);
            } catch (RuntimeException e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Could not create split: " + e.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Unauthorized User");
    }




}
