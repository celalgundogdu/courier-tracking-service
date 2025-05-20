package com.celalgundogdu.couriertracking.common.service;

import org.springframework.stereotype.Service;

@Service
public class DistanceCalculationService {

    private final DistanceCalculationStrategy distanceCalculationStrategy;

    public DistanceCalculationService(DistanceCalculationStrategy distanceCalculationStrategy) {
        this.distanceCalculationStrategy = distanceCalculationStrategy;
    }

    public Double calculateDistance(Double sourceLatitude, Double sourceLongitude, Double targetLatitude, Double targetLongitude) {
        return distanceCalculationStrategy.calculate(sourceLatitude, sourceLongitude, targetLatitude, targetLongitude);
    }
}
