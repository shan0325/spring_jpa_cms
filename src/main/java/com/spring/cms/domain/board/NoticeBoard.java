package com.spring.cms.domain.board;

import com.spring.cms.domain.Category;

import javax.persistence.*;

@Entity
public class NoticeBoard extends Board {

    @Column(length = 1, nullable = false)
    private Character noticeYn;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
