package com.spring.cms.domain;

import com.spring.cms.domain.common.BaseEntity;
import com.spring.cms.dto.MenuDto;
import com.spring.cms.enums.MenuType;
import javafx.scene.Parent;
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

    @Builder(access = AccessLevel.PRIVATE)
    public Menu(Integer level, String name, String description, Character useYn, MenuType menuType) {
        this.level = level;
        this.name = name;
        this.description = description;
        this.useYn = useYn;
        this.menuType = menuType;
    }

    //==연관관계 메서드==//
    public void addParentMenu(Menu parent) {
        this.parent = parent;
        if (parent != null) {
            parent.getChild().add(this);
        }
    }

    //==생성 메서드==//
    public static Menu createMenu(MenuDto.Create create, Menu parent) {
        Menu menu = Menu.builder()
                .level(create.getLevel())
                .name(create.getName())
                .description(create.getDescription())
                .useYn(create.getUseYn())
                .menuType(MenuType.valueOf(create.getMenuType()))
                .build();

        menu.addParentMenu(parent);
        return menu;
    }
}
