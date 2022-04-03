package com.spring.cms.config.security;

import com.spring.cms.domain.Member;
import com.spring.cms.exception.MemberException;
import com.spring.cms.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.spring.cms.exception.MemberException.MemberExceptionType.NOT_FOUND_MEMBER;


@Slf4j
@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByEmail(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new MemberException(NOT_FOUND_MEMBER));
    }

    // DB 에 User 값이 존재한다면 UserDetails 객체로 만들어서 리턴
    private UserDetails createUserDetails(Member member) {
        List<SimpleGrantedAuthority> roles = member.getMemberAuthorities().stream()
                .map(ma -> new SimpleGrantedAuthority(ma.getAuthority().getAuthority()))
                .collect(Collectors.toList());

        return new User(String.valueOf(member.getId()), member.getPassword(), roles);
    }
}
