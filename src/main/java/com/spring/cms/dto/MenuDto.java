package com.spring.cms.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.spring.cms.annotation.Enum;
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
        private Long topId;

        @NotNull(message = "레벨을 입력해주세요")
        private Integer level;

        private Integer ord;

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
    public static class QueryResponse {
        private Long id;
        private Long parentId;
        private Long topId;
        private Integer level;
        private Integer ord;
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
        public QueryResponse(Long id, Long parentId, Long topId, Integer level, Integer ord, String name, String description, Character useYn, MenuType menuType, Long boardManagerId, String link, String linkTarget, Long contentsId, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
            this.id = id;
            this.parentId = parentId;
            this.topId = topId;
            this.level = level;
            this.ord = ord;
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

    @Data
    public static class AllMenusQueryResponse {
        private Long id;
        private Long parentId;
        private Long topId;
        private Integer level;
        private Integer ord;
        private String name;

        @QueryProjection
        public AllMenusQueryResponse(Long id, Long parentId, Long topId, Integer level, Integer ord, String name) {
            this.id = id;
            this.parentId = parentId;
            this.topId = topId;
            this.level = level;
            this.ord = ord;
            this.name = name;
        }
    }

    @Data
    public static class AllMenusResponse {
        private Long id;
        private Long parentId;
        private Long topId;
        private Integer level;
        private Integer ord;
        private String name;
    }
}
