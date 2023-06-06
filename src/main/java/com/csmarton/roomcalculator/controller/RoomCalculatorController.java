package com.csmarton.roomcalculator.controller;

import com.csmarton.roomcalculator.exception.InvalidFileFormatException;
import com.csmarton.roomcalculator.model.RoomProcessOutput;
import com.csmarton.roomcalculator.service.RoomCalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class RoomCalculatorController {
    private final RoomCalculatorService roomCalculatorService;

    @PostMapping(value = "/calculate-wallpaper", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoomProcessOutput> calculateWallpaper(@RequestBody MultipartFile file) {
        try {
            RoomProcessOutput output = roomCalculatorService.calculateWallpaper(file);

            output.printResults();

            return ResponseEntity.ok(output);
        } catch (InvalidFileFormatException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
