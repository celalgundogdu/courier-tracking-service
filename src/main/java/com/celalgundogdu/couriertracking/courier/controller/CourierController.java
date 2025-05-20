package com.celalgundogdu.couriertracking.courier.controller;

import com.celalgundogdu.couriertracking.courier.dto.CourierDto;
import com.celalgundogdu.couriertracking.courier.dto.CourierRequest;
import com.celalgundogdu.couriertracking.courier.service.CourierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/couriers")
@RequiredArgsConstructor
@Tag(name = "Courier Management", description = "Courier operations")
public class CourierController {

    private final CourierService courierService;

    @Operation(summary = "Create courier", description = "It creates a courier")
    @PostMapping
    public ResponseEntity<CourierDto> createCourier(@Valid @RequestBody CourierRequest request) {
        return new ResponseEntity<>(courierService.createCourier(request), HttpStatus.CREATED);
    }

    @Operation(summary = "Get courier by id", description = "It finds the courier by id")
    @Parameter(name = "id", description = "Courier id", required = true)
    @GetMapping("/{id}")
    public ResponseEntity<CourierDto> getCourierById(@PathVariable @NotNull Long id) {
        return new ResponseEntity<>(courierService.getCourierById(id), HttpStatus.OK);
    }
}
