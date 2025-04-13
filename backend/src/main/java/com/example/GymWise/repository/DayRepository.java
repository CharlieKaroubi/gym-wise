package com.example.GymWise.repository;
import com.example.GymWise.entity.Day;
import com.example.GymWise.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DayRepository extends JpaRepository<Day, Long> {
    Day findDayById(Long id);
}
