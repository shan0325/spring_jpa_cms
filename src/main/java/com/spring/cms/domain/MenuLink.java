package com.spring.cms.domain;

import com.spring.cms.enums.MenuLinkTarget;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class MenuLink {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_link_id")
    private Long id;

    @Column(nullable = false)
    private String link;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MenuLinkTarget linkTarget;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @Builder(access = AccessLevel.PRIVATE)
    public MenuLink(String link, MenuLinkTarget linkTarget, Menu menu) {
        this.link = link;
        this.linkTarget = linkTarget;
        this.menu = menu;
    }

    //==생성 메서드==//
    public static MenuLink createMenuLink(String link, MenuLinkTarget linkTarget, Menu menu) {
        return MenuLink.builder()
                .link(link)
                .linkTarget(linkTarget)
                .menu(menu)
                .build();
    }
}
