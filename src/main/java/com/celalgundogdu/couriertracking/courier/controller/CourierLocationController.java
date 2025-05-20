package com.celalgundogdu.couriertracking.courier.controller;

import com.celalgundogdu.couriertracking.courier.dto.CourierLocationRequest;
import com.celalgundogdu.couriertracking.courier.service.CourierLocationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/courier-locations")
@RequiredArgsConstructor
@Tag(name = "Courier Location Management", description = "Courier location operations")
public class CourierLocationController {

    private final CourierLocationService courierLocationService;

    @PostMapping
    public ResponseEntity<Void> saveCourierLocation(@Valid @RequestBody CourierLocationRequest request) {
        courierLocationService.saveCourierLocation(request);
        return ResponseEntity.noContent().build();
    }
}
