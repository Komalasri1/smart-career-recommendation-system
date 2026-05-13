package com.example.careerrecommendation.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecommendationService {

    public Map<String, Object> recommendCareer(String skills, String interests) {

        skills = skills.toLowerCase();
        interests = interests.toLowerCase();

        List<String> careers = new ArrayList<>();
        List<String> suggestions = new ArrayList<>();

        int score = 50;

        if (skills.contains("java")) {
            careers.add("Software Engineer");
            careers.add("Backend Developer");
            careers.add("Java Developer");
            score += 20;

            suggestions.add("Spring Boot");
            suggestions.add("REST APIs");
        }

        if (skills.contains("sql")) {
            score += 10;
            suggestions.add("Database Optimization");
        }

        if (skills.contains("html")) {
            score += 10;
            suggestions.add("React.js");
        }

        if (interests.contains("software")) {
            score += 10;
        }

        if (careers.isEmpty()) {
            careers.add("Full Stack Developer");
        }

        Map<String, Object> result = new HashMap<>();

        result.put("careers", careers);
        result.put("score", score);
        result.put("suggestions", suggestions);

        return result;
    }
}