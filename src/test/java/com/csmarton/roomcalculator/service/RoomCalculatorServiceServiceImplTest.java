package com.csmarton.roomcalculator.service;

import com.csmarton.roomcalculator.model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("RoomCalculatorService Test")
class RoomCalculatorServiceServiceImplTest {
    private RoomCalculatorService roomCalculatorService;
    private FileIOService fileIOService;

    @BeforeEach
    void setUp() {
        fileIOService = new FileIOService();
        roomCalculatorService = new RoomCalculatorServiceImpl(fileIOService);
    }

    @Test
    void testCalculateTotalWallpaper() {
        // Given
        List<Room> rooms = Arrays.asList(
                new Room(3, 4, 5),
                new Room(6, 7, 8),
                new Room(9, 10, 11)
        );

        // Then
        int totalWallpaper = roomCalculatorService.calculateTotalWallpaper(rooms);

        // Assert
        assertEquals(1128, totalWallpaper);
    }

    @Test
    @DisplayName("Given 3 Cubic Rooms and 2 normal Room the service finds and gives back 3 cubic rooms in descending order")
    void testGetCubicRooms() {
        // Given
        List<Room> rooms = Arrays.asList(
                new Room(2, 4, 2),
                new Room(2, 2, 2),
                new Room(3, 2, 2),
                new Room(3, 3, 3),
                new Room(4, 4, 4)
        );

        // Then
        List<Room> cubicRooms = roomCalculatorService.getCubicRooms(rooms);

        // Assert
        assertEquals(3, cubicRooms.size());
        assertEquals(112, cubicRooms.get(0).getTotalWallpaper());
        assertEquals(63, cubicRooms.get(1).getTotalWallpaper());
        assertEquals(28, cubicRooms.get(2).getTotalWallpaper());
    }

    @Test
    void testGetDuplicateRooms() {
        // Given
        List<Room> rooms = Arrays.asList(
                new Room(2, 2, 2),
                new Room(3, 3, 3),
                new Room(2, 2, 2),
                new Room(4, 4, 4)
        );

        // Then
        Set<String> duplicateRooms = roomCalculatorService.getDuplicateRooms(rooms);

        // Assert
        Set<String> expectedDuplicateRooms = new HashSet<>(Arrays.asList("2x2x2"));
        assertEquals(expectedDuplicateRooms, duplicateRooms);
    }
}
