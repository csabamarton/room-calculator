package com.csmarton.roomcalculator.service;

import com.csmarton.roomcalculator.model.Room;
import com.csmarton.roomcalculator.model.RoomProcessOutput;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface RoomCalculatorService {
    RoomProcessOutput calculateWallpaper(String filePath);
    RoomProcessOutput calculateWallpaper(MultipartFile file);

    RoomProcessOutput calculateRooms(List<Room> rooms);
    Integer calculateTotalWallpaper(List<Room> rooms);
    List<Room> getCubicRooms(List<Room> rooms);
    Set<String> getDuplicateRooms(List<Room> rooms);
}