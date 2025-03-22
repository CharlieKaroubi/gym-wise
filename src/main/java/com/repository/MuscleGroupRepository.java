package com.repository;
import com.entity.muscle.MuscleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface MuscleGroupRepository extends JpaRepository<MuscleGroup, Long> {
    List<MuscleGroup> findAll();
    MuscleGroup findByName(String name);

    @Query("SELECT m FROM MuscleGroup m WHERE TYPE(m) = :type")
    List<MuscleGroup> findByType(@Param("type") Class<? extends MuscleGroup> type);
}
