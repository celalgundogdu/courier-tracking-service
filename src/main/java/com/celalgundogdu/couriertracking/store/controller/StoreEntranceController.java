package com.celalgundogdu.couriertracking.store.controller;

import com.celalgundogdu.couriertracking.common.dto.PageResponse;
import com.celalgundogdu.couriertracking.store.dto.StoreEntranceDto;
import com.celalgundogdu.couriertracking.store.service.StoreEntranceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/store-entrances")
@RequiredArgsConstructor
@Tag(name = "Store Entrance Management", description = "Store entrance operations")
public class StoreEntranceController {

    private final StoreEntranceService storeEntranceService;

    @Operation(summary = "Get store entrances by courier id", description = "It finds store entrances by courier id")
    @Parameter(name = "courierId", description = "Courier id", required = true)
    @GetMapping("/courier/{courierId}")
    public ResponseEntity<PageResponse<StoreEntranceDto>> getStoreEntrancesByCourierId(@PathVariable Long courierId,
                                                                                       @RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                                                       @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        return new ResponseEntity<>(storeEntranceService.getStoreEntrancesByCourierId(courierId, page, size), HttpStatus.OK);
    }
}
