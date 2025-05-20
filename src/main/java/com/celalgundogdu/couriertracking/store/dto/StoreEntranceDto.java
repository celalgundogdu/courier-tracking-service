package com.celalgundogdu.couriertracking.store.dto;

import com.celalgundogdu.couriertracking.store.entity.StoreEntrance;

import java.time.LocalDateTime;

public record StoreEntranceDto(
        Long courierId,
        Long storeId,
        LocalDateTime entranceTime
) {
    public static StoreEntranceDto convert(StoreEntrance storeEntrance) {
        return new StoreEntranceDto(storeEntrance.getCourier().getId(), storeEntrance.getStore().getId(), storeEntrance.getEntranceTime());
    }
}
