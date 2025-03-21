package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * A program to read weather data from a file and perform various calculations on it.
 * @author William Jijon
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        String file = "src/main/resources/data/weatherdata.csv";
        ArrayList<WeatherData> weatherData = new ArrayList<WeatherData>();

        //testing all the methods
        storeData(file, weatherData);
        printData(weatherData);
        System.out.println();
        printAverageTemp(01, weatherData);
        findMinTempDays(5.5, weatherData);
        findNumRainyDays(weatherData);
        System.out.println();
        weatherData.stream().forEach(data -> System.out.println(weatherType(data)));
    }

    /**
     * Prints the weather data to the console.
     * @param weatherdata weather data to be printed
     */
    public static void printData(ArrayList<WeatherData> weatherdata) {
        for (WeatherData data : weatherdata) {
            System.out.println(data);
        }
    }

/**
     * Prints the average temperature for a given month.
     * @param month the month number (1-12)
     * @param weatherdata weather data to be used for calculation
     */
    public static void printAverageTemp(int month, ArrayList<WeatherData> weatherdata) {
        if (month < 1 || month > 12) {
            System.out.println("Invalid month number. Please enter a number between 1 and 12.");
            return;
        }

        List<WeatherData> filteredData;
        filteredData = weatherdata.stream()
                .filter(data -> Integer.parseInt(data.date().substring(5, 7)) == month)
                .toList();

        double totalTemp = filteredData.stream().mapToDouble(WeatherData::temperature).sum();
        double averageTemp = totalTemp / filteredData.size();
        System.out.printf("Average temperature for month " + month + ": %.2f\n", averageTemp);
    }

    /**
     * Finds the number of days with a minimum temperature above a given value(inclusive).
     * @param weatherMin the minimum temperature to filter by
     * @param weatherdata weather data to be used for calculation
     */
    public static void findMinTempDays(double weatherMin, ArrayList<WeatherData> weatherdata) {
        List<WeatherData> filteredData;
        filteredData = weatherdata.stream()
                .filter(data -> data.temperature() >= weatherMin)
                .toList();
        System.out.println("Days with minimum temperature of " + weatherMin + " or higher: "+ filteredData.size());
    }

    /**
     * Finds the number of rainy days in data set.
     * @param weatherdata weather data to be used for calculation
     */
    public static void findNumRainyDays(ArrayList<WeatherData> weatherdata){
        List<WeatherData> filteredData;
        filteredData = weatherdata.stream()
                .filter(data -> data.precipitation() > 1)
                .toList();
        System.out.println("Number of rainy days: " + filteredData.size());
    }

    /**
     * Reads the weather data from a file and stores it in an ArrayList.
     * @param filePath the path to the file to be read
     * @param weatherdata the ArrayList to store the data in
     */
    public static void storeData(String filePath, ArrayList<WeatherData> weatherdata) {
        BufferedReader reader = null;
        String line = "";

        try {
            reader = new BufferedReader(new FileReader(filePath));
            while ((line = reader.readLine()) !=  null) {
                String[] row = line.split(",");
                weatherdata.add(new WeatherData(row[0], Double.parseDouble(row[1]), Double.parseDouble(row[2]), Double.parseDouble(row[3])));
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                reader.close();
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns the weather type for a given weather data.
     * @param weatherData the weather data to be used for calculation
     * @return the weather type
     */
    public static String weatherType(WeatherData weatherData) {
        double temp = weatherData.temperature();

        return switch ((int) temp) {
            default -> temp <= 0 ? "Freezing"
                    : temp < 15 ? "Cold"
                    : temp <= 30 ? "Warm"
                    : "Hot";
        };
    }


}

/**
 * A record to store the weather data.
 * @param date the date of the weather data
 * @param temperature the temperature of the weather data
 * @param Humidity the humidity of the weather data
 * @param precipitation the precipitation of the weather data
 */
record WeatherData(String date, double temperature, double Humidity, double precipitation) {}