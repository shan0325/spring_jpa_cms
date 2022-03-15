package com.spring.cms.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.cms.dto.MemberDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    public void 회원등록테스트() {
        //given
        MemberDto.Join joinMember = MemberDto.Join.builder()
                .name("회원")
                .password("1234")
                .email("member@email.com")
                .hp("01011112222")
                .build();

        //when
        MemberDto.Response newMember = memberService.join(joinMember);
        System.out.println("newMember = " + newMember);

        //then
        Assertions.assertThat(newMember.getName()).isEqualTo("회원");
    }

    @Test
    public void objectMapperTest() throws JsonProcessingException {
        //given
        ObjectMapper objectMapper = new ObjectMapper();

        MemberDto.Join joinMember = MemberDto.Join.builder()
                .name("회원1")
                .password("1234")
                .email("member1@email.com")
                .hp("01011112222")
                .build();

        String joinMemberJson = objectMapper.writeValueAsString(joinMember);
        System.out.println("joinMemberJson = " + joinMemberJson);

        MemberDto.Join reqJoinMember = objectMapper.readValue(joinMemberJson, MemberDto.Join.class);
        System.out.println("reqJoinMember = " + reqJoinMember);
    }

}