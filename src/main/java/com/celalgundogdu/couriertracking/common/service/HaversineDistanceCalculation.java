package com.celalgundogdu.couriertracking.common.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HaversineDistanceCalculation implements DistanceCalculationStrategy {

    @Value("${calculation.earth-radius}")
    private Integer earthRadius;

    @Override
    public Double calculate(Double sourceLatitude, Double sourceLongitude, Double targetLatitude, Double targetLongitude) {
        double dLat = Math.toRadians((targetLatitude - sourceLatitude));
        double dLong = Math.toRadians((targetLongitude - sourceLongitude));

        sourceLatitude = Math.toRadians(sourceLatitude);
        targetLatitude = Math.toRadians(targetLatitude);

        double a = haversine(dLat) + Math.cos(sourceLatitude) * Math.cos(targetLatitude) * haversine(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c;
    }

    private double haversine(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }
}
