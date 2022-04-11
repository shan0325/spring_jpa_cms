package com.spring.cms.controller;

import com.spring.cms.dto.menu.MenuDto;
import com.spring.cms.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(RestControllerBase.API_URI_PREFIX)
public class MenuController {

    private final MenuService menuService;

    @PostMapping("/menus")
    public ResponseEntity<?> createMenu(@RequestBody @Valid MenuDto.Create create) {
        log.info("Request Param [{}]", create);

        return ResponseEntity.ok(menuService.createMenus(create));
    }

    @GetMapping("/menus")
    public ResponseEntity<?> getMenus() {
        return ResponseEntity.ok(menuService.getAllMenus());
    }

    @GetMapping("/menus/opti")
    public ResponseEntity<?> getMenusOpti() {
        return ResponseEntity.ok(menuService.getAllMenusOpti());
    }

}
