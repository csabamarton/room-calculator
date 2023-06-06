package com.csmarton.roomcalculator.service;

import com.csmarton.roomcalculator.exception.FileProcessingException;
import com.csmarton.roomcalculator.exception.InvalidFileFormatException;
import com.csmarton.roomcalculator.model.Room;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class FileIOServiceTest {
    private final FileIOService fileIOService = new FileIOService();

    @Test
    @DisplayName("Test processLine() with valid input")
    void testProcessLine_ValidLine_ReturnsRoom() {
        // Given
        String line = "3x11x24";

        // When
        Room room = fileIOService.processLine(line);

        // Assert
        assertEquals(3, room.getLength());
        assertEquals(11, room.getWidth());
        assertEquals(24, room.getHeight());
    }

    @Test
    @DisplayName("Test processLine() with invalid input")
    void testProcessLine_InvalidFormat_ThrowsException() {
        // Given
        String line = "2sx3x4";

        // When and Assert
        assertThrows(InvalidFileFormatException.class, () -> {
            fileIOService.processLine(line);
        });
    }

    @Test
    @DisplayName("Test processLine() with invalid empty input")
    void testProcessLine_EmptyLine_ThrowsException() {
        // Given
        String line = "";

        // When and Assert
        assertThrows(InvalidFileFormatException.class, () -> {
            fileIOService.processLine(line);
        });
    }

    @Test
    @DisplayName("Test reading rooms from file")
    void testReadRoomsFromFile_With_4_Rooms() throws URISyntaxException {
        // Given
        URI uri = getClass().getClassLoader().getResource("sample-input-4-rooms.txt").toURI();
        Path filePath = Paths.get(uri);

        // When
        List<Room> rooms = fileIOService.readRoomsFromFile(filePath.toString());

        // Assert
        assertEquals(4, rooms.size());
        assertEquals(new Room(3, 11, 24), rooms.get(0));
        assertEquals(new Room(13, 5, 19), rooms.get(1));
        assertEquals(new Room(1, 9, 27), rooms.get(2));
    }

    @Test
    @DisplayName("Test readRoomsFromFile() with valid file")
    void testReadRoomsFromFileWithValidFile() {
        String filePath = "src/test/resources/sample-input.txt";

        List<Room> rooms = fileIOService.readRoomsFromFile(filePath);

        assertEquals(1000, rooms.size());
    }

    @Test
    @DisplayName("Test readRoomsFromFile() with invalid file format")
    void testReadRoomsFromFileWithInvalidFileFormat() {
        String filePath = "src/test/resources/sample-input-4-rooms-wrong-format1.txt";

        assertThrows(InvalidFileFormatException.class, () -> {
            fileIOService.readRoomsFromFile(filePath);
        });
    }

    @Test
    @DisplayName("Test readRoomsFromFile() with non-existent file")
    void testReadRoomsFromFileWithNonExistentFile() {
        String filePath = "src/test/resources/non-existent-file.txt";

        assertThrows(FileProcessingException.class, () -> {
            fileIOService.readRoomsFromFile(filePath);
        });
    }

    @Test
    @DisplayName("Test readRoomsFromFile(MultipartFile file) with valid file")
    void testReadRoomsFromFileWithValidMultipartFile() {
        // Given
        String content = "3x11x24\n13x5x19\n1x9x27";
        MockMultipartFile multipartFile = new MockMultipartFile("file", "sample-input.txt",
                "text/plain", content.getBytes(StandardCharsets.UTF_8));

        // When
        List<Room> rooms = fileIOService.readRoomsFromFile(multipartFile);

        // Assert
        assertEquals(3, rooms.size());
    }

    @Test
    @DisplayName("Test readRoomsFromFile(MultipartFile file) with invalid file format")
    void testReadRoomsFromFileWithInvalidMultipartFileFormat() {
        // Given
        String content = "3x11x24\n2sx3x4\n1x9x27";
        MockMultipartFile multipartFile = new MockMultipartFile("file", "sample-input.txt",
                "text/plain", content.getBytes(StandardCharsets.UTF_8));

        // When and Assert
        assertThrows(InvalidFileFormatException.class, () -> {
            fileIOService.readRoomsFromFile(multipartFile);
        });
    }

    @Test
    @DisplayName("Test readRoomsFromFile(MultipartFile file) with file processing error")
    void testReadRoomsFromFileWithFileProcessingError() throws IOException {
        // Given
        MockMultipartFile multipartFile = Mockito.mock(MockMultipartFile.class);
        when(multipartFile.getInputStream()).thenThrow(IOException.class);

        // When and Assert
        assertThrows(FileProcessingException.class, () -> {
            fileIOService.readRoomsFromFile(multipartFile);
        });
    }
}