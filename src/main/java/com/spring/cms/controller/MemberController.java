package com.spring.cms.controller;

import com.spring.cms.dto.MemberDto;
import com.spring.cms.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping(RestControllerBase.API_URI_PREFIX)
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members")
    public ResponseEntity<?> getMembers(Pageable pageable) {
        return ResponseEntity.ok(memberService.getMember(pageable));
    }

    @PostMapping("/members")
    public ResponseEntity<?> createMember(@RequestBody @Valid MemberDto.Join joinMember) {
        return ResponseEntity.ok(memberService.join(joinMember));
    }
}
