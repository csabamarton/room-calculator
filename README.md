# Room Wallpaper Size Calculator
The Room Wallpaper Size Calculator is a Spring Boot application that calculates the total square feet of wallpaper needed for a list of rooms. It also identifies rooms with a cubic shape and rooms that appear more than once in the input.

## Features
- Reads room dimensions from an input file or a file uploaded through an API endpoint.
- Calculates the total square feet of wallpaper needed for all rooms.
- Identifies rooms with a cubic shape and lists them in descending order based on the total needed wallpaper.
- Identifies rooms that appear more than once in the input.

## Technologies Used
- Java
- Spring
- JUnit 5
- Mockito

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 11 or later
- Apache Maven

### Installation and Start
Clone the repository: 
    
    ```
    git clone https://github.com/your-username/room-wallpaper-calculator.git
    mvn clean package
    ```

### Usage

Run the test:

    ```
    mvn test
    ```

    ```
    [INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 1.596 s - in com.csmarton.roomcalculator.service.RoomCalculatorServiceServiceImplTest
    [INFO]
    [INFO] Results:
    [INFO]
    [INFO] Tests run: 13, Failures: 0, Errors: 0, Skipped: 0
    [INFO]
    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD SUCCESS
    [INFO] ------------------------------------------------------------------------
    [INFO] Total time:  4.260 s
    [INFO] Finished at: 2023-06-06T11:28:58+02:00
    [INFO] ------------------------------------------------------------------------

    ```

