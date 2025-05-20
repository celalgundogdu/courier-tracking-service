package com.celalgundogdu.couriertracking.courier.repository;

import com.celalgundogdu.couriertracking.courier.entity.Courier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourierRepository extends JpaRepository<Courier, Long> {

}
