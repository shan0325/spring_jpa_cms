package com.spring.cms.repository.menu;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.cms.dto.MenuDto;
import com.spring.cms.dto.QMenuDto_AllMenusQueryResponse;
import com.spring.cms.dto.QMenuDto_QueryResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.spring.cms.domain.QMenu.menu;
import static com.spring.cms.domain.QMenuBoardManager.menuBoardManager;
import static com.spring.cms.domain.QMenuContents.menuContents;
import static com.spring.cms.domain.QMenuLink.menuLink;

@RequiredArgsConstructor
public class MenuRepositoryImpl implements MenuRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public MenuDto.QueryResponse response(Long menuId) {
        return queryFactory
                .select(new QMenuDto_QueryResponse(
                        menu.id,
                        menu.parent.id,
                        menu.top.id,
                        menu.level,
                        menu.ord,
                        menu.name,
                        menu.description,
                        menu.useYn,
                        menu.menuType,
                        menuBoardManager.boardManager.id,
                        menuLink.link,
                        menuLink.linkTarget.stringValue(),
                        menuContents.contents.id,
                        menu.createdDate,
                        menu.lastModifiedDate
                ))
                .from(menu)
                .leftJoin(menu.menuBoardManager, menuBoardManager)
                .leftJoin(menu.menuLink, menuLink)
                .leftJoin(menu.menuContents, menuContents)
                .where(menu.id.eq(menuId))
                .fetchOne();
    }

    @Override
    public List<MenuDto.AllMenusQueryResponse> findAllMenus() {
        return queryFactory
                .select(new QMenuDto_AllMenusQueryResponse(
                    menu.id,
                    menu.parent.id,
                    menu.top.id,
                    menu.level,
                    menu.ord,
                    menu.name
                ))
                .from(menu)
                .fetch();
    }
}
