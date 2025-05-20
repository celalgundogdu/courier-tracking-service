package com.celalgundogdu.couriertracking.courier.dto;

import com.celalgundogdu.couriertracking.courier.entity.Courier;

public record CourierDto(
        Long id,
        String name,
        Double totalTravelDistance
) {
    public static CourierDto convert(Courier courier) {
        return new CourierDto(courier.getId(), courier.getName(), courier.getTotalTravelDistance());
    }
}
