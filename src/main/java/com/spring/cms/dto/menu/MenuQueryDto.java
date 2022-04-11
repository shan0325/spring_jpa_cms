package com.spring.cms.dto.menu;

import com.querydsl.core.annotations.QueryProjection;
import com.spring.cms.domain.Menu;
import com.spring.cms.enums.MenuType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class MenuQueryDto {

    @Data
    public static class CreateResponseQuery {
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
        public CreateResponseQuery(Long id, Long parentId, Long topId, Integer level, Integer ord, String name, String description, Character useYn, MenuType menuType, Long boardManagerId, String link, String linkTarget, Long contentsId, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
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
    public static class AllMenusResponseQuery {
        private Long id;
        private Long parentId;
        private Long topId;
        private Integer level;
        private Integer ord;
        private String name;
        private List<AllMenusResponseQuery> childMenus;

        @QueryProjection
        public AllMenusResponseQuery(Long id, Long parentId, Long topId, Integer level, Integer ord, String name) {
            this.id = id;
            this.parentId = parentId;
            this.topId = topId;
            this.level = level;
            this.ord = ord;
            this.name = name;
        }
    }
}
