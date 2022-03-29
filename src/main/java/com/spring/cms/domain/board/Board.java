package com.spring.cms.domain.board;

import com.spring.cms.domain.BoardManager;
import com.spring.cms.domain.common.BaseEntity;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    private String content;

    @Column(length = 1, nullable = false)
    private Character useYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_manager_id")
    private BoardManager boardManager;
}
