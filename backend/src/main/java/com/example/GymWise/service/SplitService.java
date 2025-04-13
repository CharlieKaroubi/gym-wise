package com.example.GymWise.service;
import com.example.GymWise.dto.CreateSplitDto;
import com.example.GymWise.dto.DayDto;
import com.example.GymWise.dto.DayExerciseDto;
import com.example.GymWise.entity.Day;
import com.example.GymWise.entity.Exercise;
import com.example.GymWise.entity.Split;
import com.example.GymWise.entity.User;
import com.example.GymWise.entity.day_exercise.DayExercise;
import com.example.GymWise.entity.day_exercise.DayExerciseId;
import com.example.GymWise.repository.ExerciseRepository;
import com.example.GymWise.repository.SplitRepository;
import com.example.GymWise.repository.UserRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class SplitService {
    private final SplitRepository splitRepository;
    private final UserRepository userRepository;
    private final EmbeddingService embeddingService;
    private final ExerciseRepository exerciseRepository;

    @Autowired
    public SplitService(SplitRepository splitRepository, UserRepository userRepository, EmbeddingService embeddingService, ExerciseRepository exerciseRepository) {
        this.splitRepository = splitRepository;
        this.userRepository = userRepository;
        this.embeddingService = embeddingService;
        this.exerciseRepository = exerciseRepository;
    }

    @Transactional
    public Split createSplit(CreateSplitDto dto, String userEmail) {
        // Look up the creator based on email
        User creator = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Create a new Split entity and set basic fields.
        Split split = new Split();
        split.setName(dto.getName());
        split.setDescription(dto.getDescription());
        split.setConcentration(dto.getConcentration());
        split.setCreator(creator);

        // Map the list of DayDto to a list of Day entities.
        List<Day> dayEntities = new ArrayList<>();
        for (DayDto dayDto : dto.getDays()) {
            Day day = new Day();
            day.setTitle(dayDto.getTitle());

            List<DayExercise> dayExerciseList = new ArrayList<>();
            for (DayExerciseDto dayExerciseDto : dayDto.getExercises()) {
                String exName = dayExerciseDto.getExerciseName();
                if (exName == null) {
                    throw new RuntimeException("Exercise name is missing.");
                }
                Exercise dbExercise = exerciseRepository.findByName(exName);
                if (dbExercise == null) {
                    throw new RuntimeException("Exercise '" + exName + "' not found.");
                }
                DayExercise de = new DayExercise();
                de.setExercise(dbExercise);
                de.setSetsReps(dayExerciseDto.getSetsReps());
                de.setDay(day);  // you will assign the Day after creating it
                day.getExercises().add(de);
                dayExerciseList.add(de);
            }
            day.setExercises(dayExerciseList);
            dayEntities.add(day);
        }

        split.setDays(dayEntities);

        String input = dto.getDescription() + " " + dto.getConcentration();
        List<Double> embedding;
        try {
            embedding = embeddingService.generateEmbeddingAsync(input).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to generate embedding", e);
        }
        split.setEmbedding(embedding);

        // Save the entire split (cascading will handle persisting Days and DayExercises).
        return splitRepository.save(split);
    }



}