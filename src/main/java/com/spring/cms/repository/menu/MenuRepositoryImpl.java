package com.spring.cms.repository.menu;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.cms.domain.Menu;
import com.spring.cms.dto.MenuDto;
import com.spring.cms.dto.QMenuDto_AllMenusQueryResponse;
import com.spring.cms.dto.QMenuDto_QueryResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.spring.cms.domain.QMenu.menu;
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
                        menu.boardManager.id,
                        menuLink.link,
                        menuLink.linkTarget.stringValue(),
                        menu.contents.id,
                        menu.createdDate,
                        menu.lastModifiedDate
                ))
                .from(menu)
                .leftJoin(menu.menuLink, menuLink)
                .where(menu.id.eq(menuId))
                .fetchOne();
    }

    @Override
    public List<MenuDto.AllMenusQueryResponse> findAllMenusDto() {
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

    @Override
    public List<Menu> findAllMenus() {
        return queryFactory
                .selectFrom(menu)
                .leftJoin(menu.parent)
                .fetchJoin()
                .orderBy(
                        menu.parent.id.asc().nullsFirst(),
                        menu.createdDate.asc()
                )
                .fetch();
    }
}
