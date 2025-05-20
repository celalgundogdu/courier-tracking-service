package com.celalgundogdu.couriertracking.courier.repository;

import com.celalgundogdu.couriertracking.courier.entity.CourierLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourierLocationRepository extends JpaRepository<CourierLocation, Long> {

    Optional<CourierLocation> findTopByCourierIdOrderByLocationTimeDesc(Long courierId);
}
