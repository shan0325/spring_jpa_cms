package com.spring.cms.config;

import com.spring.cms.exception.MemberException;
import com.spring.cms.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static com.spring.cms.exception.MemberException.MemberExceptionType.NOT_FOUND_MEMBER;

@RequiredArgsConstructor
@EnableJpaAuditing
@Configuration
public class AuditorAwareConfig {

    private final MemberRepository memberRepository;

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> {
            String email = "";

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getName() != null) {
                email = memberRepository.findById(Long.parseLong(authentication.getName()))
                        .orElseThrow(() -> new MemberException(NOT_FOUND_MEMBER))
                        .getEmail();
            }
            return Optional.of(email);
        };
    }
}
