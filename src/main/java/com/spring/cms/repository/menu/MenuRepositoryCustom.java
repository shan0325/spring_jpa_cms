package com.spring.cms.repository.menu;

import com.spring.cms.dto.MenuDto;

public interface MenuRepositoryCustom {
    MenuDto.Response response(Long id);
}
