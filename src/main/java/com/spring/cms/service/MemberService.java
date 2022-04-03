package com.spring.cms.service;

import com.spring.cms.domain.Authority;
import com.spring.cms.domain.Member;
import com.spring.cms.domain.MemberAuthority;
import com.spring.cms.dto.MemberDto;
import com.spring.cms.enums.MemberStatus;
import com.spring.cms.exception.AuthorityException;
import com.spring.cms.exception.MemberException;
import com.spring.cms.repository.AuthorityRepository;
import com.spring.cms.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.spring.cms.exception.AuthorityException.AuthorityExceptionType.NOT_EXIST_AUTHORITY;
import static com.spring.cms.exception.MemberException.MemberExceptionType.ALREADY_EXIST_MEMBER;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final AuthorityRepository authorityRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    /**
     * 회원가입
     * @param joinMember
     * @return
     */
    @Transactional // 기본이 readOnly = false
    public MemberDto.Response join(MemberDto.Join joinMember) {

        if(memberRepository.existsByEmail(joinMember.getEmail())) {
            throw new MemberException(ALREADY_EXIST_MEMBER);
        }

        Authority findAuthority = authorityRepository.findById(joinMember.getAuthorityId())
                .orElseThrow(() -> new AuthorityException(NOT_EXIST_AUTHORITY));

        MemberAuthority memberAuthority = MemberAuthority.createMemberAuthority(findAuthority);

        Member member = Member.createMember(joinMember.getName(), passwordEncoder.encode(joinMember.getPassword()),
                joinMember.getEmail(), joinMember.getHp(), MemberStatus.ACTIVITY, memberAuthority);

        memberRepository.save(member);

        return modelMapper.map(member, MemberDto.Response.class);
    }

    /**
     * 회원 리스트
     * @param pageable
     * @return
     */
    public List<MemberDto.Response> getMember(Pageable pageable) {
        return memberRepository.findAll(pageable)
                .stream()
                .map(u -> modelMapper.map(u, MemberDto.Response.class))
                .collect(Collectors.toList());
    }
}
