package com.spring.cms.controller;

import com.spring.cms.service.BoardManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(RestControllerBase.API_URI_PREFIX)
public class BoardManagerController {

    private final BoardManagerService boardManagerService;

    @PostMapping("/board-manager")
    public ResponseEntity<?> createBoardManager() {

        return null;
    }

}
