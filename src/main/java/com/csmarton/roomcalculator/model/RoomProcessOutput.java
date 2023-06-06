package com.csmarton.roomcalculator.model;

import java.util.List;
import java.util.Set;

public record RoomProcessOutput(int totalWallpaper, List<Room> cubicRooms, Set<String> duplicateRooms) {

    public void printResults() {
        System.out.println("Total square feet of wallpaper needed: " + totalWallpaper());
        System.out.println("Rooms with a cubic shape (descending order):");
        for (Room room : cubicRooms()) {
            System.out.println(room.toString());
        }
        System.out.println("Rooms appearing more than once:");
        for (String room : duplicateRooms()) {
            System.out.println(room);
        }
    }
}
