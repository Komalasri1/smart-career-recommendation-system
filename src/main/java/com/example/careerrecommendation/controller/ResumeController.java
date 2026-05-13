package com.example.careerrecommendation.controller;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/resume")
@CrossOrigin

public class ResumeController {

    @PostMapping("/upload")

    public Map<String, String> uploadResume(
            @RequestParam("file")
            MultipartFile file
    ) {

        Map<String, String> result =
                new HashMap<>();

        try {

            File tempFile =
                    File.createTempFile(
                            "resume",
                            ".pdf"
                    );

            file.transferTo(tempFile);

            PDDocument document =
                    PDDocument.load(tempFile);

            PDFTextStripper stripper =
                    new PDFTextStripper();

            String text =
                    stripper.getText(document);

            document.close();

            text = text.toLowerCase();

            String recommendation =
                    "Full Stack Developer";

            if(text.contains("java")){

                recommendation =
                        "Java Developer";
            }

            if(text.contains("python")){

                recommendation =
                        "Data Scientist";
            }

            if(text.contains("sql")){

                recommendation =
                        "Database Engineer";
            }

            result.put(
                    "recommendation",
                    recommendation
            );

        }

        catch (Exception e) {

            result.put(
                    "error",
                    e.getMessage()
            );
        }

        return result;
    }
}