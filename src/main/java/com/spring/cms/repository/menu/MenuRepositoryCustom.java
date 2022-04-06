package com.spring.cms.repository.menu;

import com.spring.cms.dto.MenuDto;

import java.util.List;

public interface MenuRepositoryCustom {
    MenuDto.QueryResponse response(Long id);
    List<MenuDto.AllMenusQueryResponse> findAllMenus();
}
