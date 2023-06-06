package com.csmarton.roomcalculator.controller;

import com.csmarton.roomcalculator.exception.InvalidFileFormatException;
import com.csmarton.roomcalculator.model.RoomProcessOutput;
import com.csmarton.roomcalculator.service.RoomCalculatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RoomCalculatorController {
    private final RoomCalculatorService roomCalculatorService;

    @PostMapping(value = "/calculate-wallpaper", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoomProcessOutput> calculateWallpaper(@RequestBody MultipartFile file) {
        try {
            log.info("Calculating wallpaper from uploaded file: {}", file.getOriginalFilename());

            RoomProcessOutput output = roomCalculatorService.calculateWallpaper(file);

            log.info("Wallpaper calculation completed successfully.");
            return ResponseEntity.ok(output);
        } catch (InvalidFileFormatException e) {
            log.error("Invalid file format: {}", e.getMessage());
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            log.error("Error occurred during wallpaper calculation: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
