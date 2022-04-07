package com.spring.cms.service;

import com.spring.cms.domain.Menu;
import com.spring.cms.dto.MenuDto;
import com.spring.cms.repository.menu.MenuRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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

        List<Menu> allMenus = menuRepository.findAllMenus();
        System.out.println("allMenus = " + allMenus);
    }
}