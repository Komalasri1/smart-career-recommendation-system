package com.example.careerrecommendation.controller;

import com.example.careerrecommendation.model.UserProfile;
import com.example.careerrecommendation.service.RecommendationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/career")
@CrossOrigin

public class CareerController {

    @Autowired
    private RecommendationService service;

    @PostMapping("/recommend")

    public Map<String, Object> recommend(
            @RequestBody UserProfile profile
    ) {

        Map<String, Object> result =

                service.recommendCareer(
                        profile.getSkills(),
                        profile.getInterests()
                );

        profile.setRecommendation(

                ((List<String>) result.get("careers")).get(0)
        );

        return result;
    }
}