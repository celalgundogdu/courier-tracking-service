package com.celalgundogdu.couriertracking.store.repository;

import com.celalgundogdu.couriertracking.store.entity.StoreEntrance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface StoreEntranceRepository extends JpaRepository<StoreEntrance, Long> {

    boolean existsByCourierIdAndStoreIdAndEntranceTimeAfter(Long courierId, Long storeId, LocalDateTime entranceTime);

    Page<StoreEntrance> findByCourierId(Long courierId, Pageable pageable);
}
