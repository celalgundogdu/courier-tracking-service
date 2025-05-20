package com.celalgundogdu.couriertracking.courier.controller;

import com.celalgundogdu.couriertracking.courier.dto.CourierDto;
import com.celalgundogdu.couriertracking.courier.dto.CourierRequest;
import com.celalgundogdu.couriertracking.courier.service.CourierService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CourierController.class)
class CourierControllerTest {

    @MockBean
    private CourierService courierService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenCourierExists_shouldReturnCourierDto() throws Exception {
        Long courierId = 123L;
        CourierDto courierDto = new CourierDto(courierId, "test", 1.1);
        when(courierService.getCourierById(any())).thenReturn(courierDto);

        mockMvc.perform(get("/api/v1/couriers/{id}", courierId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(courierId))
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.totalTravelDistance").value(1.1));

        verify(courierService, times(1)).getCourierById(courierId);
    }

    @Test
    void whenInputIsValid_shouldCreateCourier() throws Exception {
        Long courierId = 123L;
        CourierRequest request = new CourierRequest("test");
        CourierDto courierDto = new CourierDto(courierId, "test", 0.0);
        when(courierService.createCourier(any())).thenReturn(courierDto);

        mockMvc.perform(post("/api/v1/couriers", courierId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(courierId))
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.totalTravelDistance").value(0.0));

        verify(courierService, times(1)).createCourier(request);
    }
}