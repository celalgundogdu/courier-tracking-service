package com.celalgundogdu.couriertracking.store.config;

import com.celalgundogdu.couriertracking.store.dto.StoreLoadDto;
import com.celalgundogdu.couriertracking.store.entity.Store;
import com.celalgundogdu.couriertracking.store.repository.StoreRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class StoreDataLoader {

    private static final Logger logger = LoggerFactory.getLogger(StoreDataLoader.class);
    private final StoreRepository storeRepository;
    private final ObjectMapper objectMapper;

    public StoreDataLoader(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
        objectMapper = new ObjectMapper();
    }

    @PostConstruct
    public void init() {
        TypeReference<List<StoreLoadDto>> typeReference = new TypeReference<>() {};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/static/stores.json");
        try {
            List<StoreLoadDto> storeLoadDtos = objectMapper.readValue(inputStream, typeReference);
            List<Store> stores = getStores(storeLoadDtos);
            if (!CollectionUtils.isEmpty(stores)) {
                storeRepository.saveAll(stores);
                logger.info("Saved {} stores", stores.size());
            } else {
                logger.warn("No saved any store from file");
            }
        } catch (IOException e) {
            logger.error("Occurred error while loading stores: {}", e.getMessage());
        }
    }

    private List<Store> getStores(List<StoreLoadDto> storeLoadDtos) {
        return storeLoadDtos.stream().map(dto -> {
            Store store = new Store();
            store.setName(dto.name());
            store.setLatitude(dto.lat());
            store.setLongitude(dto.lng());
            return store;
        }).toList();
    }
}
