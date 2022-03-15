package com.spring.cms.config;

import com.spring.cms.domain.Member;
import com.spring.cms.exception.EntityNotFoundException;
import com.spring.cms.exception.MemberNotFoundException;
import com.spring.cms.repository.MemberRepository;
import com.spring.cms.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@EnableJpaAuditing
@Configuration
public class AuditorAwareConfig {

    private final MemberRepository memberRepository;

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> {
            Long memberId = SecurityUtil.getCurrentMemberId();

            Member findMember = memberRepository.findById(memberId)
                    .orElseThrow(() -> new MemberNotFoundException());

            return Optional.of(findMember.getEmail());
        };
    }
}
