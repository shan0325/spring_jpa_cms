package com.spring.cms.domain;

import com.spring.cms.domain.common.BaseEntity;
import com.spring.cms.enums.MemberStatus;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    private String hp;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberStatus status;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberAuthority> memberAuthorities = new ArrayList<>();

    @Builder(access = AccessLevel.PRIVATE)
    public Member(String name, String password, String email, String hp, MemberStatus status) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.hp = hp;
        this.status = status;
    }

    //==생성 메서드==//
    public static Member createMember(String name, String password, String email, String hp, MemberStatus status, MemberAuthority... memberAuthoritys) {
        Member member = Member.builder()
                .name(name)
                .password(password)
                .email(email)
                .hp(hp)
                .status(status)
                .build();

        for (MemberAuthority memberAuthority : memberAuthoritys) {
            memberAuthority.addMemberAuthority(member);
        }
        return member;
    }
}
