package com.spring.cms.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;


public class MemberDto {

    @ToString
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Join {

        @NotBlank(message = "이름을 입력해주세요")
        private String name;
        
        @NotBlank(message = "비밀번호를 입력해주세요")
        private String password;
        
        @NotBlank(message = "이메일을 입력해주세요")
        @Email(message = "이메일을 정확히 입력해주세요")
        private String email;

        @Pattern(regexp = "[0-9]{10,11}", message = "10~11자리의 숫자만 입력가능합니다")
        private String hp;
        
        @NotNull(message = "권한을 선택해주세요")
        private Long authorityId;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Login {
        @NotBlank(message = "이메일을 입력해주세요")
        @Email(message = "이메일을 정확히 입력해주세요")
        private String email;

        @NotBlank(message = "비밀번호를 입력해주세요")
        private String password;

    }

    @Data
    public static class Response {
        private Long id;
        private String name;
        private String email;
        private String hp;
        private String createBy;
        private String lastModifiedBy;
        private LocalDateTime createdDate;
        private LocalDateTime lastModifiedDate;
    }

}
