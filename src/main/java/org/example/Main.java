package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String file = "src/main/resources/data/weatherdata.csv";
        ArrayList<WeatherData> weatherData = new ArrayList<WeatherData>();
        storeData(file, weatherData);
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

}

record WeatherData(String date, double temperature, double Humidity, double precipitation) {}