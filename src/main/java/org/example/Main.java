package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String file = "src/main/resources/data/weatherdata.csv";
        ArrayList<WeatherData> weatherData = new ArrayList<WeatherData>();
        storeData(file, weatherData);
        printAverageTemp(01, weatherData);
    }

    public static void printData(ArrayList<WeatherData> weatherdata) {
        for (WeatherData data : weatherdata) {
            System.out.println(data);
        }
    }

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

    //TODO: Created a method to find days that meet the minimum temperature for a given set of data
    public static void findMinTempDays(double weatherMin, ArrayList<WeatherData> weatherdata) {}

    //TODO: Create a method to find and print the number of rainy days in a given set of data
    public static void findNumRainyDays(ArrayList<WeatherData> weatherdata){}

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
}

record WeatherData(String date, double temperature, double Humidity, double precipitation) {}