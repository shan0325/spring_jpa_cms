package com.spring.cms.dto.menu;

import com.spring.cms.annotation.Enum;
import com.spring.cms.domain.Menu;
import com.spring.cms.enums.MenuType;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    public static class CreateResponse {
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
    }

    @Data
    public static class AllMenusResponse {
        private Long id;
        private Long parentId;
        private Long topId;
        private Integer level;
        private Integer ord;
        private String name;
        private List<AllMenusResponse> childMenus;

        public AllMenusResponse(Menu menu) {
            this.id = menu.getId();
            this.parentId = menu.getParent() != null ? menu.getParent().getId() : null;
            this.topId = menu.getTop() != null ? menu.getTop().getId() : null;
            this.level = menu.getLevel();
            this.ord = menu.getOrd();
            this.name = menu.getName();
            this.childMenus = menu.getChild().stream()
                    .map(AllMenusResponse::new)
                    .collect(Collectors.toList());
        }
    }

    @Data
    public static class MenuDetailResponse {
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
    }

    @Getter
    @Builder
    @ToString
    public static class Update {
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
    public static class UpdateResponse {
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
    }
}
