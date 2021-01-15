package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin("*")
@RestController
public class FileUploader {
    List<String> files = new ArrayList<String>();
    private final Path rootLocation = Paths.get("_Path_To_Save_The_File");

    @PostMapping("/savefile")
    public ResponseEntity<String> handleFileUpload(@RequestBody MultipartFile file) {
        String message;
        try {
            try {
                System.out.println(file.getOriginalFilename());
                Files.copy(file.getInputStream(), this.rootLocation.resolve("file"));
            } catch (Exception e) {
                throw new RuntimeException("FAIL!");
            }
            files.add(file.getOriginalFilename());
            message = "Successfully uploaded!";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            System.out.println("error"+e.getMessage());
            message = "Failed to upload!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }
}
