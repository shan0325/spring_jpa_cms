package com.spring.cms.controller;

import com.spring.cms.dto.MemberDto;
import com.spring.cms.dto.TokenDto;
import com.spring.cms.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping(RestControllerBase.API_URI_PREFIX +  "/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto.Generate> login(@RequestBody @Valid MemberDto.Login login) {
        return ResponseEntity.ok(authService.login(login));
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto.Generate> reissue(@RequestBody @Valid TokenDto.Reissue reissue) {
        return ResponseEntity.ok(authService.reissue(reissue));
    }
}
