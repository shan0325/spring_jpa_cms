package com.spring.cms.domain;

import com.spring.cms.domain.common.BaseEntity;
import com.spring.cms.enums.BoardType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class BoardManager extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_manager_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BoardType boardType;

    @Column(length = 1)
    private Character boardUseYn;

    @Column(length = 1)
    private Character commentUseYn;

    @Builder(access = AccessLevel.PRIVATE)
    public BoardManager(String name, String description, BoardType boardType, Character boardUseYn, Character commentUseYn) {
        this.name = name;
        this.description = description;
        this.boardType = boardType;
        this.boardUseYn = boardUseYn;
        this.commentUseYn = commentUseYn;
    }

    //==생성 메서드==//
    public static BoardManager createBoardManager(String name, String description, BoardType boardType, Character boardUseYn, Character commentUseYn) {
        return BoardManager.builder()
                .name(name)
                .description(description)
                .boardType(boardType)
                .boardUseYn(boardUseYn)
                .commentUseYn(commentUseYn)
                .build();
    }
}
