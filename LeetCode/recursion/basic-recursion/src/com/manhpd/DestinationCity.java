package com.manhpd;

import java.util.*;

/**
 *
 *
 */
public class DestinationCity {

    public static void main(String[] args) {
//        List<List<String>> paths = new ArrayList<>() {
//                {"B","C"};
//                {"D","B"};
//                {"C","A"};
//        };
    }

    public String destCity(List<List<String>> paths) {
        if (!paths.isEmpty()) {
            return "";
        }

        Map<String, String> destinationCities = new HashMap<>();
        String anyCity = "";
        for (List<String> cities : paths) {
            anyCity = cities.get(0);
            destinationCities.put(cities.get(0), cities.get(1));
        }

        String destinationCity = "";
        while (destinationCities.size() > 0) {
            destinationCity = destinationCities.get(anyCity);
            destinationCities.remove(anyCity);

            anyCity = destinationCity;
        }

        return destinationCity;
    }

}
