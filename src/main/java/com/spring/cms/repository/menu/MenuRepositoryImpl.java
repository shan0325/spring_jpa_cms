package com.spring.cms.repository.menu;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.cms.domain.*;
import com.spring.cms.dto.MenuDto;
import com.spring.cms.dto.QMenuDto_Response;
import lombok.RequiredArgsConstructor;

import static com.spring.cms.domain.QBoardManager.boardManager;
import static com.spring.cms.domain.QMenu.*;
import static com.spring.cms.domain.QMenuBoardManager.menuBoardManager;
import static com.spring.cms.domain.QMenuContents.menuContents;
import static com.spring.cms.domain.QMenuLink.menuLink;

@RequiredArgsConstructor
public class MenuRepositoryImpl implements MenuRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public MenuDto.Response response(Long menuId) {
        return queryFactory
                .select(new QMenuDto_Response(
                        menu.id,
                        menu.level,
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
                .leftJoin(menuBoardManager)
                    .on(menu.id.eq(menuBoardManager.menu.id))
                .leftJoin(menuLink)
                    .on(menu.id.eq(menuLink.id))
                .leftJoin(menuContents)
                    .on(menu.id.eq(menuContents.menu.id))
                .where(menu.id.eq(menuId))
                .fetchOne();
    }
}
