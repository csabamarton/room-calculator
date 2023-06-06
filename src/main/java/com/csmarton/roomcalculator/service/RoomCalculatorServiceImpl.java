package com.csmarton.roomcalculator.service;

import com.csmarton.roomcalculator.model.Room;
import com.csmarton.roomcalculator.model.RoomProcessOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class RoomCalculatorServiceImpl implements RoomCalculatorService {

    private final FileIOService fileIOService;

    public RoomCalculatorServiceImpl(FileIOService fileIOService) {
        this.fileIOService = fileIOService;
    }

    @Override
    public RoomProcessOutput calculateWallpaper(String filePath) {
        log.info("Calculating wallpaper from file: {}", filePath);

        List<Room> rooms = fileIOService.readRoomsFromFile(filePath);
        if (rooms.isEmpty()) {
            log.error("Failed to read input file.");
            return new RoomProcessOutput(0, new ArrayList<>(), new HashSet<>());
        }

        return calculateRooms(rooms);
    }

    @Override
    public RoomProcessOutput calculateRooms(List<Room> rooms) {
        int totalWallpaper = calculateTotalWallpaper(rooms);
        List<Room> cubicRooms = getCubicRooms(rooms);
        Set<String> duplicateRooms = getDuplicateRooms(rooms);

        RoomProcessOutput result = new RoomProcessOutput(totalWallpaper, cubicRooms, duplicateRooms);

        log.info("Printing results:");
        result.printResults();

        return result;
    }

    @Override
    public Integer calculateTotalWallpaper(List<Room> rooms) {
        int totalWallpaper = 0;
        for (Room room : rooms) {
            totalWallpaper += room.getTotalWallpaper();
        }
        return totalWallpaper;
    }

    @Override
    public List<Room> getCubicRooms(List<Room> rooms) {
        List<Room> cubicRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isCubicShape()) {
                cubicRooms.add(room);
            }
        }
        cubicRooms.sort((r1, r2) -> r2.getTotalWallpaper() - r1.getTotalWallpaper());
        return cubicRooms;
    }

    @Override
    public Set<String> getDuplicateRooms(List<Room> rooms) {
        Set<String> duplicateRooms = new HashSet<>();
        Set<String> uniqueRooms = new HashSet<>();
        for (Room room : rooms) {
            String roomString = room.toString();
            if (!uniqueRooms.add(roomString)) {
                duplicateRooms.add(roomString);
            }
        }
        return duplicateRooms;
    }
}
