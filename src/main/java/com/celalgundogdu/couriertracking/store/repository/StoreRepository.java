package com.celalgundogdu.couriertracking.store.repository;

import com.celalgundogdu.couriertracking.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query(value = """
        SELECT * FROM store s
        WHERE (
            :earthRadius * acos(
                cos(radians(:lat)) * cos(radians(s.latitude)) *
                cos(radians(s.longitude) - radians(:lng)) +
                sin(radians(:lat)) * sin(radians(s.latitude))
            )
        ) <= :radius
        """, nativeQuery = true)
    List<Store> findNearbyStores(
            @Param("lat") double latitude,
            @Param("lng") double longitude,
            @Param("radius") double radius,
            @Param("earthRadius") double earthRadius
    );
}
