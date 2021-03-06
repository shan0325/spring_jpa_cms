package com.spring.cms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.cms.domain.Contents;
import com.spring.cms.dto.menu.MenuDto;
import com.spring.cms.enums.MenuType;
import com.spring.cms.repository.BoardManagerRepository;
import com.spring.cms.repository.ContentsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
                .topId(null)
                .level(0)
                .ord(1)
                .name("?????????")
                .description("?????????")
                .useYn('Y')
                .menuType("BOARD")
                .boardManagerId(1L)
                .build();

        this.mockMvc.perform(post(RestControllerBase.API_URI_PREFIX + "/menus")
                .header("Authorization", "Bearer " + this.accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(create))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("menus/create",
                        requestFields(
                                fieldWithPath("parentId").type(JsonFieldType.NUMBER).description("?????????????????????").optional(),
                                fieldWithPath("topId").type(JsonFieldType.NUMBER).description("????????????????????????").optional(),
                                fieldWithPath("level").type(JsonFieldType.NUMBER).description("????????????"),
                                fieldWithPath("ord").type(JsonFieldType.NUMBER).description("????????????"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("?????????"),
                                fieldWithPath("description").type(JsonFieldType.STRING).description("????????????").optional(),
                                fieldWithPath("useYn").type(JsonFieldType.STRING).description("????????????"),
                                fieldWithPath("menuType").type(JsonFieldType.STRING).description("????????????"),
                                fieldWithPath("boardManagerId").type(JsonFieldType.NUMBER).description("????????????????????????").optional(),
                                fieldWithPath("link").type(JsonFieldType.STRING).description("??????????????????").optional(),
                                fieldWithPath("linkTarget").type(JsonFieldType.STRING).description("????????????????????????").optional(),
                                fieldWithPath("contentsId").type(JsonFieldType.NUMBER).description("??????????????????").optional()
                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("???????????????"),
                                fieldWithPath("parentId").type(JsonFieldType.NUMBER).description("?????????????????????").optional(),
                                fieldWithPath("topId").type(JsonFieldType.NUMBER).description("????????????????????????").optional(),
                                fieldWithPath("level").type(JsonFieldType.NUMBER).description("????????????"),
                                fieldWithPath("ord").type(JsonFieldType.NUMBER).description("????????????"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("?????????"),
                                fieldWithPath("description").type(JsonFieldType.STRING).description("????????????").optional(),
                                fieldWithPath("useYn").type(JsonFieldType.STRING).description("????????????"),
                                fieldWithPath("menuType").type(JsonFieldType.STRING).description("????????????"),
                                fieldWithPath("boardManagerId").type(JsonFieldType.NUMBER).description("????????????????????????").optional(),
                                fieldWithPath("link").type(JsonFieldType.STRING).description("??????????????????").optional(),
                                fieldWithPath("linkTarget").type(JsonFieldType.STRING).description("????????????????????????").optional(),
                                fieldWithPath("contentsId").type(JsonFieldType.NUMBER).description("??????????????????").optional(),
                                fieldWithPath("createdDate").type(JsonFieldType.STRING).description("????????????"),
                                fieldWithPath("lastModifiedDate").type(JsonFieldType.STRING).description("????????????")
                        )
                ));
    }

    @Test
    @Rollback(value = false)
    public void createMenuLink() throws Exception {
        MenuDto.Create create = MenuDto.Create.builder()
                .parentId(null)
                .topId(null)
                .level(0)
                .ord(1)
                .name("????????????")
                .description("????????????")
                .useYn('Y')
                .menuType("LINK")
                .link("https://www.naver.com")
                .linkTarget("BLANK")
                .build();

        this.mockMvc.perform(post(RestControllerBase.API_URI_PREFIX + "/menus")
                .header("Authorization", "Bearer " + this.accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(create))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Rollback(value = false)
    public void createMenuMenu() throws Exception {
        MenuDto.Create create = MenuDto.Create.builder()
                .parentId(null)
                .topId(null)
                .level(0)
                .ord(1)
                .name("??????")
                .description("??????")
                .useYn('Y')
                .menuType("MENU")
                .build();

        this.mockMvc.perform(post(RestControllerBase.API_URI_PREFIX + "/menus")
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
        Contents contents = Contents.createContents("???????????????", "???????????????~~", 'Y');
        contentsRepository.save(contents);

        MenuDto.Create create = MenuDto.Create.builder()
                .parentId(null)
                .topId(null)
                .level(0)
                .ord(1)
                .name("?????????")
                .description("?????????")
                .useYn('Y')
                .menuType("CONTENTS")
                .contentsId(contents.getId())
                .build();

        this.mockMvc.perform(post(RestControllerBase.API_URI_PREFIX + "/menus")
                .header("Authorization", "Bearer " + this.accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(create))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getMenus() throws Exception {
        this.mockMvc.perform(get(RestControllerBase.API_URI_PREFIX + "/menus")
                .header("Authorization", "Bearer " + this.accessToken)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("menus/getMenus",
                        responseFields(
                                fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("???????????????"),
                                fieldWithPath("[].parentId").type(JsonFieldType.NUMBER).description("?????????????????????").optional(),
                                fieldWithPath("[].topId").type(JsonFieldType.NUMBER).description("????????????????????????").optional(),
                                fieldWithPath("[].level").type(JsonFieldType.NUMBER).description("????????????"),
                                fieldWithPath("[].ord").type(JsonFieldType.NUMBER).description("????????????"),
                                fieldWithPath("[].name").type(JsonFieldType.STRING).description("?????????"),
                                subsectionWithPath("[].childMenus").type(JsonFieldType.ARRAY).description("????????????").optional()
                        )
                ));
    }

    @Test
    public void getMenusOpti() throws Exception {
        this.mockMvc.perform(get(RestControllerBase.API_URI_PREFIX + "/menus/opti")
                .header("Authorization", "Bearer " + this.accessToken)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getMenuDetail() throws Exception {
        this.mockMvc.perform(get(RestControllerBase.API_URI_PREFIX + "/menus/{menuId}", 1L)
                .header("Authorization", "Bearer " + this.accessToken)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
        .andDo(document("menus/menuDetail",
                responseFields(
                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("???????????????"),
                        fieldWithPath("parentId").type(JsonFieldType.NUMBER).description("?????????????????????").optional(),
                        fieldWithPath("topId").type(JsonFieldType.NUMBER).description("????????????????????????").optional(),
                        fieldWithPath("level").type(JsonFieldType.NUMBER).description("????????????"),
                        fieldWithPath("ord").type(JsonFieldType.NUMBER).description("????????????"),
                        fieldWithPath("name").type(JsonFieldType.STRING).description("?????????"),
                        fieldWithPath("description").type(JsonFieldType.STRING).description("????????????").optional(),
                        fieldWithPath("useYn").type(JsonFieldType.STRING).description("????????????"),
                        fieldWithPath("menuType").type(JsonFieldType.STRING).description("????????????"),
                        fieldWithPath("boardManagerId").type(JsonFieldType.NUMBER).description("????????????????????????").optional(),
                        fieldWithPath("link").type(JsonFieldType.STRING).description("??????????????????").optional(),
                        fieldWithPath("linkTarget").type(JsonFieldType.STRING).description("????????????????????????").optional(),
                        fieldWithPath("contentsId").type(JsonFieldType.NUMBER).description("??????????????????").optional(),
                        fieldWithPath("createdDate").type(JsonFieldType.STRING).description("????????????"),
                        fieldWithPath("lastModifiedDate").type(JsonFieldType.STRING).description("????????????")
                )));
    }

    @Test
    @Rollback(value = false)
    public void updateMenu() throws Exception {
        MenuDto.Update update = MenuDto.Update.builder()
                .name("????????????_update")
                .description("????????????_update")
                .useYn('N')
                .menuType(MenuType.BOARD.name())
                .boardManagerId(1L)
                .build();

        this.mockMvc.perform(put(RestControllerBase.API_URI_PREFIX + "/menus/{menuId}", 1L)
                .header("Authorization", "Bearer " + this.accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(update))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("menus/update",
                        requestFields(
                                fieldWithPath("name").type(JsonFieldType.STRING).description("?????????"),
                                fieldWithPath("description").type(JsonFieldType.STRING).description("????????????").optional(),
                                fieldWithPath("useYn").type(JsonFieldType.STRING).description("????????????"),
                                fieldWithPath("menuType").type(JsonFieldType.STRING).description("????????????"),
                                fieldWithPath("boardManagerId").type(JsonFieldType.NUMBER).description("????????????????????????").optional(),
                                fieldWithPath("link").type(JsonFieldType.STRING).description("??????????????????").optional(),
                                fieldWithPath("linkTarget").type(JsonFieldType.STRING).description("????????????????????????").optional(),
                                fieldWithPath("contentsId").type(JsonFieldType.NUMBER).description("??????????????????").optional()
                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("???????????????"),
                                fieldWithPath("parentId").type(JsonFieldType.NUMBER).description("?????????????????????").optional(),
                                fieldWithPath("topId").type(JsonFieldType.NUMBER).description("????????????????????????").optional(),
                                fieldWithPath("level").type(JsonFieldType.NUMBER).description("????????????"),
                                fieldWithPath("ord").type(JsonFieldType.NUMBER).description("????????????"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("?????????"),
                                fieldWithPath("description").type(JsonFieldType.STRING).description("????????????").optional(),
                                fieldWithPath("useYn").type(JsonFieldType.STRING).description("????????????"),
                                fieldWithPath("menuType").type(JsonFieldType.STRING).description("????????????"),
                                fieldWithPath("boardManagerId").type(JsonFieldType.NUMBER).description("????????????????????????").optional(),
                                fieldWithPath("link").type(JsonFieldType.STRING).description("??????????????????").optional(),
                                fieldWithPath("linkTarget").type(JsonFieldType.STRING).description("????????????????????????").optional(),
                                fieldWithPath("contentsId").type(JsonFieldType.NUMBER).description("??????????????????").optional(),
                                fieldWithPath("createdDate").type(JsonFieldType.STRING).description("????????????"),
                                fieldWithPath("lastModifiedDate").type(JsonFieldType.STRING).description("????????????")
                        )
                ));
    }

    @Test
    public void deleteMenu() throws Exception {
        //given
        MenuDto.Create create = MenuDto.Create.builder()
                .parentId(null)
                .topId(null)
                .level(0)
                .ord(1)
                .name("????????????")
                .description("????????????")
                .useYn('Y')
                .menuType("LINK")
                .link("https://www.naver.com")
                .linkTarget("BLANK")
                .build();

        ResultActions result = this.mockMvc.perform(post(RestControllerBase.API_URI_PREFIX + "/menus")
                .header("Authorization", "Bearer " + this.accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(create))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        String content = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        Map<String, Object> contentMap = jsonParser.parseMap(content);
        Integer id = (Integer) contentMap.get("id");

        //when
        this.mockMvc.perform(delete(RestControllerBase.API_URI_PREFIX + "/menus/{menuId}", id)
                .header("Authorization", "Bearer " + this.accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("menus/delete"));
    }

}