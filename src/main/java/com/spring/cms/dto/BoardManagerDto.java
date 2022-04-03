package com.spring.cms.dto;


import com.spring.cms.annotation.Enum;
import com.spring.cms.enums.BoardType;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.aspectj.lang.annotation.DeclareAnnotation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BoardManagerDto {

    @Getter
    @Builder
    @ToString
    public static class Create {
        @NotBlank(message = "게시판명을 입력해주세요")
        private String name;

        private String description;

        @Enum(enumClass = BoardType.class)
        private String boardType;

        @NotNull(message = "게시판 사용유무를 입력해주세요")
        private Character boardUseYn;

        @NotNull(message = "댓글 사용유무를 입력해주세요")
        private Character commentUseYn;
    }

    @Data
    public static class Response {
        private Long id;
        private String name;
        private String description;
        private BoardType boardType;
        private Character boardUseYn;
        private Character commentUseYn;
    }
}
