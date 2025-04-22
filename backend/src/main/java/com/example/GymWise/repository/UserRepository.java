package com.example.GymWise.repository;


import com.example.GymWise.entity.Split;
import com.example.GymWise.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findByVerificationCode(String verificationCode);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.savedSplits WHERE u.email = :email")
    Optional<User> findByEmailWithSplits(String email);
}
