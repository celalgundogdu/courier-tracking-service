package com.celalgundogdu.couriertracking.common.service;

public interface DistanceCalculationStrategy {

    Double calculate(Double sourceLatitude, Double sourceLongitude, Double targetLatitude, Double targetLongitude);
}
