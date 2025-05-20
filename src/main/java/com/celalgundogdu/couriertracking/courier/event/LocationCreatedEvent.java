package com.celalgundogdu.couriertracking.courier.event;

import java.time.LocalDateTime;

public record LocationCreatedEvent (
        Long courierId,
        LocalDateTime locationTime,
        Double previousLatitude,
        Double previousLongitude,
        Double currentLatitude,
        Double currentLongitude
) {
}
