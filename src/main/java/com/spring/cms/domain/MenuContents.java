package com.spring.cms.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class MenuContents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_contents_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contents_id")
    private Contents contents;

    @Builder(access = AccessLevel.PRIVATE)
    public MenuContents(Menu menu, Contents contents) {
        this.menu = menu;
        this.contents = contents;
    }

    //==생성 메서드==//
    public static MenuContents createMenuContents(Menu menu, Contents contents) {
        return MenuContents.builder()
                .menu(menu)
                .contents(contents)
                .build();
    }
}
