package com.spring.cms.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Access Token 과 Refresh Token 을 함께 사용하기 때문에 저장이 필요합니다.
 * 보통은 Token 이 만료될 때 자동으로 삭제 처리 하기 위해 Redis 를 많이 사용하지만, 귀찮으니 일단 임시로 RDB 에 저장하는 방식으로 구현했습니다.
 * 만약 지금 예제처럼 RDB 를 저장소로 사용한다면 배치 작업을 통해 만료된 토큰들을 삭제해주는 작업이 필요합니다.
 */

@NoArgsConstructor
@Getter
@Entity
public class RefreshToken {

    @Id
    private String key;
    private String value;

    public RefreshToken updateValue(String token) {
        this.value = token;
        return this;
    }

    @Builder
    public RefreshToken(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
