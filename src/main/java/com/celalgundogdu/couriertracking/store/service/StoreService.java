package com.celalgundogdu.couriertracking.store.service;

import com.celalgundogdu.couriertracking.store.entity.Store;
import com.celalgundogdu.couriertracking.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    @Value("${calculation.store-radius}")
    private Integer storeRadius;
    @Value("${calculation.earth-radius}")
    private Integer earthRadius;

    private final StoreRepository storeRepository;

    public List<Store> findNearByStores(Double latitude, Double longitude) {
        return storeRepository.findNearbyStores(latitude, longitude, storeRadius, earthRadius);
    }
}
