package com.celalgundogdu.couriertracking.courier.dto;

import jakarta.validation.constraints.NotNull;

public record CourierLocationRequest(
        @NotNull
        Long courierId,
        @NotNull
        Double latitude,
        @NotNull
        Double longitude
) {
}
