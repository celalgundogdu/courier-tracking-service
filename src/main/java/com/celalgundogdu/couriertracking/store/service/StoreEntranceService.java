package com.celalgundogdu.couriertracking.store.service;

import com.celalgundogdu.couriertracking.common.dto.PageResponse;
import com.celalgundogdu.couriertracking.courier.entity.Courier;
import com.celalgundogdu.couriertracking.store.dto.StoreEntranceDto;
import com.celalgundogdu.couriertracking.store.entity.Store;
import com.celalgundogdu.couriertracking.store.entity.StoreEntrance;
import com.celalgundogdu.couriertracking.store.repository.StoreEntranceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreEntranceService {

    private final StoreEntranceRepository storeEntranceRepository;

    public void createStoreEntrance(Store store, Courier courier, LocalDateTime entranceTime, Double latitude, Double longitude) {
        StoreEntrance storeEntrance = new StoreEntrance();
        storeEntrance.setStore(store);
        storeEntrance.setCourier(courier);
        storeEntrance.setLatitude(latitude);
        storeEntrance.setLongitude(longitude);
        storeEntrance.setEntranceTime(entranceTime);
        storeEntranceRepository.save(storeEntrance);
    }

    public boolean isReEntrance(Long courierId, Long storeId) {
        return storeEntranceRepository.existsByCourierIdAndStoreIdAndEntranceTimeAfter(courierId, storeId, LocalDateTime.now().minusMinutes(1));
    }

    public PageResponse<StoreEntranceDto> getStoreEntrancesByCourierId(Long courierId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("entranceTime").descending());
        Page<StoreEntrance> storeEntrances = storeEntranceRepository.findByCourierId(courierId, pageable);
        List<StoreEntranceDto> storeEntranceDtos = storeEntrances.stream()
                .map(StoreEntranceDto::convert)
                .toList();
        return new PageResponse<>(storeEntranceDtos, storeEntrances.getNumber(), storeEntrances.getSize(), storeEntrances.getTotalElements(),
                storeEntrances.getTotalPages(), storeEntrances.isFirst(), storeEntrances.isLast());
    }
}
