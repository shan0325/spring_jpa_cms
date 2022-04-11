package com.spring.cms.repository.menu;

import com.spring.cms.domain.Menu;
import com.spring.cms.dto.menu.MenuQueryDto;

import java.util.List;

public interface MenuRepositoryCustom {
    MenuQueryDto.CreateResponseQuery findCreatedMenu(Long id);
    List<MenuQueryDto.AllMenusResponseQuery> findMenus(boolean parentIsNull, List<Long> menuIds);
}
