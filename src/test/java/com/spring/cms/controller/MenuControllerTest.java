package com.spring.cms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.cms.domain.BoardManager;
import com.spring.cms.domain.Contents;
import com.spring.cms.dto.MenuDto;
import com.spring.cms.enums.BoardType;
import com.spring.cms.enums.MenuType;
import com.spring.cms.repository.BoardManagerRepository;
import com.spring.cms.repository.ContentsRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@SpringBootTest
@Transactional
class MenuControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BoardManagerRepository boardManagerRepository;

    @Autowired
    private ContentsRepository contentsRepository;

    private String accessToken;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() throws Exception {
        this.accessToken = AuthControllerTest.obtainAccessToken(mockMvc, "admin@naver.com", "1234");
    }

    @Test
    @Rollback(value = false)
    public void createMenuBoard() throws Exception {
        MenuDto.Create create = MenuDto.Create.builder()
                .parentId(null)
                .level(0)
                .name("게시판")
                .description("게시판")
                .useYn('Y')
                .menuType("BOARD")
                .boardManagerId(1L)
                .build();

        this.mockMvc.perform(post(RestControllerBase.API_URI_PREFIX + "/menu")
                .header("Authorization", "Bearer " + this.accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(create))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("menu/create",
                        requestFields(
                                fieldWithPath("parentId").type(JsonFieldType.NUMBER).description("부모메뉴아이디").optional(),
                                fieldWithPath("level").type(JsonFieldType.NUMBER).description("메뉴뎁스"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("메뉴명"),
                                fieldWithPath("description").type(JsonFieldType.STRING).description("메뉴설명").optional(),
                                fieldWithPath("useYn").type(JsonFieldType.STRING).description("사용유무"),
                                fieldWithPath("menuType").type(JsonFieldType.STRING).description("메뉴타입"),
                                fieldWithPath("boardManagerId").type(JsonFieldType.NUMBER).description("게시판관리아이디").optional(),
                                fieldWithPath("link").type(JsonFieldType.STRING).description("바로가기링크").optional(),
                                fieldWithPath("linkTarget").type(JsonFieldType.STRING).description("바로가기링크타겟").optional(),
                                fieldWithPath("contentsId").type(JsonFieldType.NUMBER).description("컨텐츠아이디").optional()
                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("메뉴아이디"),
                                fieldWithPath("level").type(JsonFieldType.NUMBER).description("메뉴뎁스"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("메뉴명"),
                                fieldWithPath("description").type(JsonFieldType.STRING).description("메뉴설명").optional(),
                                fieldWithPath("useYn").type(JsonFieldType.STRING).description("사용유무"),
                                fieldWithPath("menuType").type(JsonFieldType.STRING).description("메뉴타입"),
                                fieldWithPath("boardManagerId").type(JsonFieldType.NUMBER).description("게시판관리아이디").optional(),
                                fieldWithPath("link").type(JsonFieldType.STRING).description("바로가기링크").optional(),
                                fieldWithPath("linkTarget").type(JsonFieldType.STRING).description("바로가기링크타겟").optional(),
                                fieldWithPath("contentsId").type(JsonFieldType.NUMBER).description("컨텐츠아이디").optional(),
                                fieldWithPath("createdDate").type(JsonFieldType.STRING).description("생성일시"),
                                fieldWithPath("lastModifiedDate").type(JsonFieldType.STRING).description("수정일시")
                        )
                ));
    }

    @Test
    @Rollback(value = false)
    public void createMenuLink() throws Exception {
        MenuDto.Create create = MenuDto.Create.builder()
                .parentId(null)
                .level(0)
                .name("바로가기")
                .description("바로가기")
                .useYn('Y')
                .menuType("LINK")
                .link("https://www.naver.com")
                .linkTarget("BLANK")
                .build();

        this.mockMvc.perform(post(RestControllerBase.API_URI_PREFIX + "/menu")
                .header("Authorization", "Bearer " + this.accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(create))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Rollback(value = false)
    public void createMenuEmpty() throws Exception {
        MenuDto.Create create = MenuDto.Create.builder()
                .parentId(null)
                .level(0)
                .name("메뉴")
                .description("메뉴")
                .useYn('Y')
                .menuType("EMPTY")
                .build();

        this.mockMvc.perform(post(RestControllerBase.API_URI_PREFIX + "/menu")
                .header("Authorization", "Bearer " + this.accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(create))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Rollback(value = false)
    public void createMenuContents() throws Exception {
        Contents contents = Contents.createContents("뉴스컨텐츠", "오늘의뉴스~~", 'Y');
        contentsRepository.save(contents);

        MenuDto.Create create = MenuDto.Create.builder()
                .parentId(null)
                .level(0)
                .name("컨텐츠")
                .description("컨텐츠")
                .useYn('Y')
                .menuType("CONTENTS")
                .contentsId(contents.getId())
                .build();

        this.mockMvc.perform(post(RestControllerBase.API_URI_PREFIX + "/menu")
                .header("Authorization", "Bearer " + this.accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(create))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

}