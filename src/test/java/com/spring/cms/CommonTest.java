package com.spring.cms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.spring.cms.error.apiError.ApiError;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

public class CommonTest {

    @Test
    public void apiErrorTest() throws JsonProcessingException {

        ApiError apiError = new ApiError(HttpStatus.OK, "111111111111");

        List<FieldError> fieldErrorList = new ArrayList<>();
        fieldErrorList.add(new FieldError("Member", "email", "이메일을 입력해주세요"));
        fieldErrorList.add(new FieldError("Member", "password", "비밀번호를 입력해주세요"));

        apiError.addValidationErrors(fieldErrorList);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.registerModule(new JavaTimeModule()).writeValueAsString(apiError);
        System.out.println("json = " + json);

        System.out.println("apiError = " + apiError);
    }
}
