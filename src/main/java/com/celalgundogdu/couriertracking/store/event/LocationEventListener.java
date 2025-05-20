package com.celalgundogdu.couriertracking.store.event;

import com.celalgundogdu.couriertracking.common.exception.EntityNotExistsException;
import com.celalgundogdu.couriertracking.common.service.DistanceCalculationService;
import com.celalgundogdu.couriertracking.courier.entity.Courier;
import com.celalgundogdu.couriertracking.courier.event.LocationCreatedEvent;
import com.celalgundogdu.couriertracking.courier.repository.CourierLocationRepository;
import com.celalgundogdu.couriertracking.courier.repository.CourierRepository;
import com.celalgundogdu.couriertracking.store.entity.Store;
import com.celalgundogdu.couriertracking.store.service.StoreEntranceService;
import com.celalgundogdu.couriertracking.store.service.StoreService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LocationEventListener {

    private final CourierRepository courierRepository;
    private final CourierLocationRepository courierLocationRepository;
    private final StoreService storeService;
    private final StoreEntranceService storeEntranceService;
    private final DistanceCalculationService distanceCalculationService;

    @EventListener
    @Transactional
    public void handleLocationCreatedEvent(LocationCreatedEvent event) {
        Courier courier = courierRepository.findById(event.courierId())
                .orElseThrow(() -> new EntityNotExistsException("Courier not found with id: " + event.courierId()));

        Double distance = distanceCalculationService.calculateDistance(event.previousLatitude(), event.previousLongitude(), event.currentLatitude(), event.currentLongitude());
        courier.setTotalTravelDistance(courier.getTotalTravelDistance() + distance);
        courierRepository.save(courier);

        List<Store> nearByStores = storeService.findNearByStores(event.currentLatitude(), event.currentLongitude());
        nearByStores.forEach(store -> {
            boolean isReEntrance = storeEntranceService.isReEntrance(event.courierId(), store.getId());
            if (!isReEntrance) {
                storeEntranceService.createStoreEntrance(store, courier, event.locationTime(), event.currentLatitude(), event.currentLongitude());
            }
        });
    }
}
