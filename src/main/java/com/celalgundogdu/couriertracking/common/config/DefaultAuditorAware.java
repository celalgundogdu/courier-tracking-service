package com.celalgundogdu.couriertracking.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class DefaultAuditorAware implements AuditorAware<String> {

    @Value("${audit.user:system}")
    private String defaultUser;

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(defaultUser);
    }
}
