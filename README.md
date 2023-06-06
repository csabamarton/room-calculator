# Room Wallpaper Size Calculator
The Room Wallpaper Size Calculator is a Spring Boot application that calculates the total square feet of wallpaper needed for a list of rooms. It also identifies rooms with a cubic shape and rooms that appear more than once in the input.

## Features
- Reads room dimensions from an input file or a file uploaded through an API endpoint.
- Calculates the total square feet of wallpaper needed for all rooms.
- Identifies rooms with a cubic shape and lists them in descending order based on the total needed wallpaper.
- Identifies rooms that appear more than once in the input.

## Technologies Used
- Java
- Spring Boot
- Spring Web
- Swagger (OpenAPI)
- JUnit 5
- Mockito

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 11 or later
- Apache Maven

### Installation and Start
1. Clone the repository: 
```
git clone https://github.com/your-username/room-wallpaper-calculator.git
```
3. Run the application using the following command:
```
mvn clean package
mvn spring-boot:run
```
5. Open your web browser and access the Swagger UI at http://localhost:8080/swagger-ui/index.html to view the available endpoints and interact with the API.
6. Use the provided input file as an example to create your own input file with room dimensions. Example file format:
```
3x11x24
13x5x19
1x9x27
24x8x21
```
7. To calculate the wallpaper for rooms using an input file, use the following API endpoint:
- Endpoint: POST /calculate-wallpaper
- Request Body: Upload the input file
- The calculated results will be returned as a JSON response, including the total square feet of wallpaper needed, rooms with a cubic shape, and rooms appearing more than once:
```json
{
  "totalWallpaper": 1592486,
  "cubicRooms": [
    {
      "length": 28,
      "width": 28,
      "height": 28,
      "totalWallpaper": 5488,
      "cubicShape": true
    },
    {
      "length": 7,
      "width": 7,
      "height": 7,
      "totalWallpaper": 343,
      "cubicShape": true
    }
  ],
  "duplicateRooms": [
    "8x8x16",
    "15x26x22",
    "4x3x23",
    "15x10x7",
    "8x28x29",
    "2x25x8",
    "17x15x2",
    "22x27x12",
    "6x18x15",
    "17x25x1",
    "7x3x4",
    "6x8x12",
    "22x3x1"
  ]
}
```

