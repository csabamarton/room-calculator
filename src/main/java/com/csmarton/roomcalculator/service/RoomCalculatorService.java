package com.csmarton.roomcalculator.service;

import com.csmarton.roomcalculator.model.Room;
import com.csmarton.roomcalculator.model.RoomProcessOutput;

import java.util.List;
import java.util.Set;

public interface RoomCalculatorService {
    RoomProcessOutput calculateWallpaper(String filePath);

    RoomProcessOutput calculateRooms(List<Room> rooms);
    Integer calculateTotalWallpaper(List<Room> rooms);
    List<Room> getCubicRooms(List<Room> rooms);
    Set<String> getDuplicateRooms(List<Room> rooms);
}