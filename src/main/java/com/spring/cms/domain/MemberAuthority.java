package com.spring.cms.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class MemberAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_authority_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authority_id")
    private Authority authority;

    public MemberAuthority(Authority authority) {
        this.authority = authority;
    }

    //==연관관계 메서드==//
    public void addMemberAuthority(Member member) {
        if (this.member != null) {
            this.member.getMemberAuthorities().remove(this);
        }
        this.member = member;
        member.getMemberAuthorities().add(this);
    }

    //==생성 메서드==//
    public static MemberAuthority createMemberAuthority(Authority authority) {
        return new MemberAuthority(authority);
    }

}
