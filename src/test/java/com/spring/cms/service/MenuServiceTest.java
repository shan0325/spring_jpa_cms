package com.spring.cms.service;

import com.spring.cms.dto.menu.MenuDto;
import com.spring.cms.repository.menu.MenuRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class MenuServiceTest {

    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Test
    public void getAllMenus() {

        List<MenuDto.AllMenusResponse> allMenus = menuService.getAllMenus();
        System.out.println("allMenus = " + allMenus);
    }
}