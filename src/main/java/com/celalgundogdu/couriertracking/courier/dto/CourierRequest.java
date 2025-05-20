package com.celalgundogdu.couriertracking.courier.dto;

import com.celalgundogdu.couriertracking.courier.annonation.ValidCourierName;

public record CourierRequest(
        @ValidCourierName
        String name
) {
}
