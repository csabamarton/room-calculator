package com.csmarton.roomcalculator.service;

import com.csmarton.roomcalculator.exception.FileProcessingException;
import com.csmarton.roomcalculator.exception.InvalidFileFormatException;
import com.csmarton.roomcalculator.model.Room;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileIOService {
    private static final String DIMENSION_REGEX = "^\\d+x\\d+x\\d+$";

    public List<Room> readRoomsFromFile(String filePath) {
        List<Room> rooms = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Path.of(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Room room = processLine(line);
                rooms.add(room);
            }
        } catch (IOException e) {
            throw new FileProcessingException("Error reading the input file: " + e.getMessage());
        }
        return rooms;
    }

    public List<Room> readRoomsFromFile(MultipartFile file) {
        List<Room> rooms = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Room room = processLine(line);
                rooms.add(room);
            }
        } catch (IOException e) {
            throw new FileProcessingException("Error reading the input file: " + e.getMessage());
        }
        return rooms;
    }

    Room processLine(String line) {
        if (!line.matches(DIMENSION_REGEX)) {
            throw new InvalidFileFormatException("Invalid format in the input file: " + line);
        }
        String[] dimensions = line.split("x");
        int length = Integer.parseInt(dimensions[0]);
        int width = Integer.parseInt(dimensions[1]);
        int height = Integer.parseInt(dimensions[2]);
        Room room = new Room(length, width, height);

        return room;
    }
}
