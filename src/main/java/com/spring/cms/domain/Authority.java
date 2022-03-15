package com.spring.cms.domain;

import com.spring.cms.domain.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Authority extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id")
    private Long id;

    @Column(nullable = false)
    private String authority;

    @Column(nullable = false)
    private String authorityName;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Builder(access = AccessLevel.PRIVATE)
    public Authority(String authority, String authorityName, LocalDateTime createdDate) {
        this.authority = authority;
        this.authorityName = authorityName;
        this.createdDate = createdDate;
    }

    //==생성 메서드==//
    public static Authority createAuthority(String authority, String authorityName) {
        return Authority.builder()
                .authority(authority)
                .authorityName(authorityName)
                .createdDate(LocalDateTime.now())
                .build();
    }

}
