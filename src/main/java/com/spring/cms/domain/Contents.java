package com.spring.cms.domain;

import com.spring.cms.domain.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Contents extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contents_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    private String content;

    @Column(length = 1, nullable = false)
    private Character useYn;

    @Builder(access = AccessLevel.PRIVATE)
    public Contents(String title, String content, Character useYn) {
        this.title = title;
        this.content = content;
        this.useYn = useYn;
    }

    //==생성 메서드==//
    public static Contents createContents(String title, String content, Character useYn) {
        return Contents.builder()
                .title(title)
                .content(content)
                .useYn(useYn)
                .build();
    }
}
