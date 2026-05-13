package com.example.careerrecommendation.repository;

import com.example.careerrecommendation.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserProfile, Long> {

}