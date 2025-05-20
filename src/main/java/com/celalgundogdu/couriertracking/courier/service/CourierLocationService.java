package com.celalgundogdu.couriertracking.courier.service;

import com.celalgundogdu.couriertracking.common.exception.EntityNotExistsException;
import com.celalgundogdu.couriertracking.courier.dto.CourierLocationRequest;
import com.celalgundogdu.couriertracking.courier.entity.Courier;
import com.celalgundogdu.couriertracking.courier.entity.CourierLocation;
import com.celalgundogdu.couriertracking.courier.event.LocationCreatedEvent;
import com.celalgundogdu.couriertracking.courier.repository.CourierLocationRepository;
import com.celalgundogdu.couriertracking.courier.repository.CourierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourierLocationService {

    private final CourierRepository courierRepository;
    private final CourierLocationRepository courierLocationRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public void saveCourierLocation(CourierLocationRequest request) {
        Courier courier = courierRepository.findById(request.courierId())
                .orElseThrow(() -> new EntityNotExistsException("Courier not found with id: " + request.courierId()));
        LocalDateTime now = LocalDateTime.now();
        CourierLocation courierLocation = CourierLocation.builder()
                .courier(courier)
                .latitude(request.latitude())
                .longitude(request.longitude())
                .locationTime(now)
                .build();
        Optional<CourierLocation> previousLocationOptional = courierLocationRepository.findTopByCourierIdOrderByLocationTimeDesc(request.courierId());
        courierLocationRepository.save(courierLocation);
        previousLocationOptional.ifPresent(previousLocation -> publishLocationCreatedEvent(request, previousLocation, now));
    }

    private void publishLocationCreatedEvent(CourierLocationRequest request, CourierLocation previousLocation, LocalDateTime locationTime) {
        LocationCreatedEvent locationCreatedEvent = new LocationCreatedEvent(
                request.courierId(),
                locationTime,
                previousLocation.getLatitude(),
                previousLocation.getLongitude(),
                request.latitude(),
                request.longitude());
        applicationEventPublisher.publishEvent(locationCreatedEvent);
    }
}
