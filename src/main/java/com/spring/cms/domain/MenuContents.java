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
public class MenuContents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_contents_id")
    private Long id;

    @JsonIgnore
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
        MenuContents menuContents = MenuContents.builder()
                .menu(menu)
                .contents(contents)
                .build();

        menu.setMenuContents(menuContents);

        return menuContents;
    }
}
