package com.example.GymWise.repository;
import com.example.GymWise.entity.Exercise;
import com.example.GymWise.entity.Split;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SplitRepository extends JpaRepository<Split, Long> {

    @Query(value = """
        SELECT *
        FROM splits
        ORDER BY embedding <=> CAST(:embedding AS vector)
        LIMIT 3
    """, nativeQuery = true)
    List<Split> findTop3MostSimilar(@Param("embedding") String embedding);

}
