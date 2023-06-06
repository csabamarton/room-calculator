package com.csmarton.roomcalculator.model;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;

@Slf4j
public record RoomProcessOutput(int totalWallpaper, List<Room> cubicRooms, Set<String> duplicateRooms) {

    public void printResults() {
        log.info("Total square feet of wallpaper needed: {}", totalWallpaper());
        log.info("Rooms with a cubic shape (descending order):");
        for (Room room : cubicRooms()) {
            log.info(room.toString());
        }
        log.info("Rooms appearing more than once:");
        for (String room : duplicateRooms()) {
            log.info(room);
        }
    }
}
