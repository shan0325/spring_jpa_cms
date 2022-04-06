package com.spring.cms.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@NoArgsConstructor
@Getter
@Entity
public class MenuBoardManager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_board_manager_id")
    private Long id;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_manager_id", nullable = false)
    private BoardManager boardManager;

    @Builder(access = AccessLevel.PRIVATE)
    public MenuBoardManager(Menu menu, BoardManager boardManager) {
        this.menu = menu;
        this.boardManager = boardManager;
    }

    //==생성 메서드==//
    public static MenuBoardManager createMenuBoardManager(Menu menu, BoardManager boardManager) {
        MenuBoardManager menuBoardManager = MenuBoardManager.builder()
                .menu(menu)
                .boardManager(boardManager)
                .build();

        menu.setMenuBoardManager(menuBoardManager);

        return menuBoardManager;
    }
}
