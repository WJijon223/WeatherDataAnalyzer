package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String file = "src/main/resources/data/weatherdata.csv";
        ArrayList<WeatherData> weatherData = new ArrayList<WeatherData>();
        storeData(file, weatherData);
        printData(weatherData);
    }

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

    public static void printData(ArrayList<WeatherData> weatherdata) {
        for (WeatherData data : weatherdata) {
            System.out.println(data);
        }
    }

    //TODO: Create a method to find and print average temperature for a given month
    public static void printAverageTemp(String month, ArrayList<WeatherData> weatherdata) {}

    //TODO: Created a method to find days that meet the minimum temperature for a given set of data
    public static void findMinTempDays(double weatherMin, ArrayList<WeatherData> weatherdata) {}

    //TODO: Create a method to find and print the number of rainy days in a given set of data
    public static void findNumRainyDays(ArrayList<WeatherData> weatherdata){}
}

record WeatherData(String date, double temperature, double Humidity, double precipitation) {}