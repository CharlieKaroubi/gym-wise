package com.example.GymWise.controller;
import com.example.GymWise.dto.CreateSplitDto;
import com.example.GymWise.dto.QuerySplitDto;
import com.example.GymWise.entity.Exercise;
import com.example.GymWise.entity.Split;
import com.example.GymWise.repository.SplitRepository;
import com.example.GymWise.service.ExerciseService;
import com.example.GymWise.service.SplitService;
import org.apache.coyote.Response;
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
                Split created = splitService.createSplit(createSplitDto, email);
                return ResponseEntity.status(HttpStatus.CREATED).body(created);
            } catch (RuntimeException e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Could not create split: " + e.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Unauthorized User");
    }

    @DeleteMapping
    public ResponseEntity<Void> removeSplit(@RequestParam Long splitId) {
        splitService.removeSplitById(splitId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/similar")
    public ResponseEntity<List<Split>> getTop3SimilarSplits(@RequestBody QuerySplitDto querySplitDto) {
        String concentration = querySplitDto.getConcentration();
        String input = querySplitDto.getInput();
        return ResponseEntity.ok(splitService.getTop3SimilarSplits(concentration, input));
    }

    @GetMapping
    public ResponseEntity<List<Split>> getAllSplits() {
        return ResponseEntity.ok(splitService.getAllSplits());
    }

}
