# Weather Data Analysis

## Overview
This project reads weather data from a CSV file and performs various calculations on it, such as printing the data, calculating average temperatures, finding days with minimum temperatures above a certain value, and counting the number of rainy days.

## Features
- Read weather data from a CSV file
- Print weather data to the console
- Calculate and print the average temperature for a given month
- Find and print the number of days with a minimum temperature above a given value
- Find and print the number of rainy days
- Determine the weather type based on temperature

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven

### Running the Project
1. Clone the repository:
    ```sh
    git clone <repository-url>
    ```
2. Navigate to the project directory:
    ```sh
    cd <project-directory>
    ```
3. Compile and run the project using Maven:
    ```sh
    mvn clean install
    mvn exec:java -Dexec.mainClass="org.example.Main"
    ```

## Usage
The `Main` class contains the main method which performs the following operations:
- Reads weather data from `src/main/resources/data/weatherdata.csv`
- Prints the weather data
- Calculates and prints the average temperature for January
- Finds and prints the number of days with a minimum temperature of 5.5°C or higher
- Finds and prints the number of rainy days
- Prints the weather type for each day

## Author
- William Jijon

## License
This project is licensed under the MIT License.
