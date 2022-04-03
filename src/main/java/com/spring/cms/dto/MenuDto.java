package com.spring.cms.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.spring.cms.annotation.Enum;
import com.spring.cms.domain.MenuContents;
import com.spring.cms.enums.MenuType;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class MenuDto {

    @Getter
    @Builder
    @ToString
    public static class Create {
        private Long parentId;

        @NotNull(message = "레벨을 입력해주세요")
        private Integer level;

        @NotBlank(message = "메뉴명을 입력해주세요")
        private String name;

        private String description;

        @NotNull(message = "사용유무를 입력해주세요")
        private Character useYn;

        @Enum(enumClass = MenuType.class)
        @NotBlank(message = "메뉴타입을 입력해주세요")
        private String menuType;

        private Long boardManagerId;
        private String link;
        private String linkTarget;
        private Long contentsId;
    }

    @Data
    public static class Response {
        private Long id;
        private Integer level;
        private String name;
        private String description;
        private Character useYn;
        private MenuType menuType;
        private Long boardManagerId;
        private String link;
        private String linkTarget;
        private Long contentsId;
        private LocalDateTime createdDate;
        private LocalDateTime lastModifiedDate;

        @QueryProjection
        public Response(Long id, Integer level, String name, String description, Character useYn, MenuType menuType, Long boardManagerId, String link, String linkTarget, Long contentsId, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
            this.id = id;
            this.level = level;
            this.name = name;
            this.description = description;
            this.useYn = useYn;
            this.menuType = menuType;
            this.boardManagerId = boardManagerId;
            this.link = link;
            this.linkTarget = linkTarget;
            this.contentsId = contentsId;
            this.createdDate = createdDate;
            this.lastModifiedDate = lastModifiedDate;
        }
    }
}
