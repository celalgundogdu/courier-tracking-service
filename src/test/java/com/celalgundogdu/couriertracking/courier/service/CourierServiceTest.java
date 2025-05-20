package com.celalgundogdu.couriertracking.courier.service;

import com.celalgundogdu.couriertracking.courier.dto.CourierDto;
import com.celalgundogdu.couriertracking.courier.entity.Courier;
import com.celalgundogdu.couriertracking.courier.repository.CourierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourierServiceTest {

    private CourierService courierService;

    @Mock
    private CourierRepository courierRepository;

    @BeforeEach
    void setUp() {
        courierService = new CourierService(courierRepository);
    }

    @Test
    void whenCourierExists_shouldReturnCourierDto() {
        Long courierId = 123L;
        Courier courier = new Courier();
        courier.setId(1L);
        courier.setName("test");
        courier.setTotalTravelDistance(1.1);
        when(courierRepository.findById(any())).thenReturn(Optional.of(courier));

        CourierDto response = courierService.getCourierById(courierId);
        verify(courierRepository, times(1)).findById(courierId);
        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(courier.getId());
        assertThat(response.name()).isEqualTo(courier.getName());
        assertThat(response.totalTravelDistance()).isEqualTo(courier.getTotalTravelDistance());
    }
}