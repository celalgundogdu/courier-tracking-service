package com.celalgundogdu.couriertracking.courier.service;

import com.celalgundogdu.couriertracking.common.exception.EntityNotExistsException;
import com.celalgundogdu.couriertracking.courier.dto.CourierDto;
import com.celalgundogdu.couriertracking.courier.dto.CourierRequest;
import com.celalgundogdu.couriertracking.courier.entity.Courier;
import com.celalgundogdu.couriertracking.courier.repository.CourierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourierService {

    private final CourierRepository courierRepository;

    public CourierDto createCourier(CourierRequest request) {
        Courier courier = Courier.builder()
                .name(request.name())
                .build();
        Courier savedCourier = courierRepository.save(courier);
        return CourierDto.convert(savedCourier);
    }

    public CourierDto getCourierById(Long id) {
        Courier courier = courierRepository.findById(id)
                .orElseThrow(() -> new EntityNotExistsException("Courier not found with id: " + id));
        return CourierDto.convert(courier);
    }
}
