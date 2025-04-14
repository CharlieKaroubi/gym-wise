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
import com.pgvector.PGvector;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.filter.FilterExpressionBuilder;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ai.document.Document;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class SplitService {
    @Autowired
    private final VectorStore vectorStore;

    private final SplitRepository splitRepository;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;

    @Autowired
    public SplitService(SplitRepository splitRepository,
                        UserRepository userRepository,
                        ExerciseRepository exerciseRepository,
                        VectorStore vectorStore) {
        this.splitRepository = splitRepository;
        this.userRepository = userRepository;
        this.exerciseRepository = exerciseRepository;
        this.vectorStore = vectorStore;
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
            day.setSplit(split);
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
                de.setDay(day);
                day.getExercises().add(de);
                dayExerciseList.add(de);
            }
            day.setExercises(dayExerciseList);
            dayEntities.add(day);
        }

        split.setDays(dayEntities);
        Split savedSplit = splitRepository.save(split);

        String input = split.getName() + " " + split.getDescription();
        Document doc = new Document(
                input,
                Map.of(
                        "splitId", savedSplit.getId()
                )
        );
        vectorStore.add(List.of(doc));
        return savedSplit;
    }

    public void removeSplitById(Long splitId) {
        splitRepository.deleteById(splitId);
        FilterExpressionBuilder builder = new FilterExpressionBuilder();

        vectorStore.delete(
                builder.eq("splitId", splitId).build()
        );
    }


}