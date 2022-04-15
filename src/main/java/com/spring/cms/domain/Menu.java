package com.spring.cms.domain;

import com.spring.cms.domain.common.BaseEntity;
import com.spring.cms.dto.menu.MenuDto;
import com.spring.cms.enums.MenuType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
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
    @OrderBy("ord asc, createdDate desc")
    private List<Menu> child = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "top_id")
    private Menu top;

    @Column(nullable = false)
    private Integer level;

    private Integer ord;

    @Column(length = 100, nullable = false)
    private String name;

    private String description;

    @Column(length = 1, nullable = false)
    private Character useYn;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MenuType menuType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_manager_id")
    private BoardManager boardManager;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "menu_link_id")
    private MenuLink menuLink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contents_id")
    private Contents contents;

    @Builder(access = AccessLevel.PRIVATE)
    public Menu(Menu top, Integer level, Integer ord, String name, String description, Character useYn,
                MenuType menuType, BoardManager boardManager, MenuLink menuLink, Contents contents) {
        this.top = top;
        this.level = level;
        this.ord = ord;
        this.name = name;
        this.description = description;
        this.useYn = useYn;
        this.menuType = menuType;
        this.boardManager = boardManager;
        this.menuLink = menuLink;
        this.contents = contents;
    }

    //==연관관계 메서드==//
    public void addParentMenu(Menu parent) {
        this.parent = parent;
        if (parent != null) {
            parent.getChild().add(this);
        }
    }

    //==생성 메서드==//
    public static Menu createMenu(MenuDto.Create create, Menu parent, Menu top, BoardManager boardManager, MenuLink menuLink, Contents contents) {
        Menu menu = Menu.builder()
                .top(top)
                .level(create.getLevel())
                .ord(create.getOrd())
                .name(create.getName())
                .description(create.getDescription())
                .useYn(create.getUseYn())
                .menuType(MenuType.valueOf(create.getMenuType()))
                .boardManager(boardManager)
                .menuLink(menuLink)
                .contents(contents)
                .build();

        menu.addParentMenu(parent);
        return menu;
    }

    //==수정 메서드==//
    public void updateMenu(MenuDto.Update update, BoardManager boardManager, MenuLink menuLink, Contents contents) {
        this.name = update.getName();
        this.description = update.getDescription();
        this.useYn = update.getUseYn();
        this.menuType = MenuType.valueOf(update.getMenuType());
        this.boardManager = boardManager;
        this.menuLink = menuLink;
        this.contents = contents;
    }
}
