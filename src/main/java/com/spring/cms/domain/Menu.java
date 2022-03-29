package com.spring.cms.domain;

import com.spring.cms.domain.common.BaseEntity;
import com.spring.cms.enums.MenuType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Menu parent;

    @OneToMany(mappedBy = "parent")
    private List<Menu> child = new ArrayList<>();

    @Column(nullable = false)
    private Integer level;

    @Column(length = 100, nullable = false)
    private String name;

    private String description;

    @Column(length = 1, nullable = false)
    private Character useYn;

    @Enumerated(EnumType.STRING)
    private MenuType menuType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_manager_id")
    private BoardManager boardManager;

    private void setParent(Menu parent) {
        this.parent = parent;
    }

    //==연관관계 메서드==//
    public void addChildMenu(Menu child) {
        this.child.add(child);
        child.setParent(this);
    }
}
