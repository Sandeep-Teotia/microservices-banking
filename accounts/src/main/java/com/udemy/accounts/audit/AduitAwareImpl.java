package com.udemy.accounts.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class AduitAwareImpl implements AuditorAware<String> {

    @Override
    @SuppressWarnings("null")
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Sandeep_Admin");
    }
}
