package com.spring.cms;

import com.spring.cms.domain.Authority;
import com.spring.cms.dto.MemberDto;
import com.spring.cms.repository.AuthorityRepository;
import com.spring.cms.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitDbService initDbService;

    @PostConstruct
    public void init() {
        initDbService.init();
    }

    @Component
    @RequiredArgsConstructor
    static class InitDbService {
        private final AuthorityRepository authorityRepository;
        private final MemberService memberService;

        @Transactional
        public void init() {
            /*Authority authorityAdmin = Authority.createAuthority("ROLE_ADMIN", "관리자");
            authorityRepository.save(authorityAdmin);

            Authority authorityUser = Authority.createAuthority("ROLE_USER", "일반회원");
            authorityRepository.save(authorityUser);

            MemberDto.Join joinMember2 = MemberDto.Join.builder()
                    .name("관리자")
                    .password("1234")
                    .email("admin@email.com")
                    .authorityId(authorityAdmin.getId())
                    .build();

            memberService.join(joinMember2);

            MemberDto.Join joinMember = MemberDto.Join.builder()
                    .name("유저")
                    .password("1234")
                    .email("member@email.com")
                    .hp("01011112222")
                    .authorityId(authorityUser.getId())
                    .build();

            memberService.join(joinMember);*/
        }
    }
}
