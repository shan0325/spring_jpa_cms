package com.spring.cms.controller;

import com.spring.cms.dto.MenuDto;
import com.spring.cms.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(RestControllerBase.API_URI_PREFIX)
public class MenuController {

    private final MenuService menuService;

    @PostMapping("/menu")
    public ResponseEntity<?> createMenu(@RequestBody @Valid MenuDto.Create create) {
        log.info("Request Param [{}]", create);

        return ResponseEntity.ok(menuService.createMenu(create));
    }

}
