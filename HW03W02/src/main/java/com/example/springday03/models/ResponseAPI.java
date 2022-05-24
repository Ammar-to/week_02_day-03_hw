package com.example.springday03.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data @AllArgsConstructor
public class ResponseAPI {
    private String message;
    private Integer code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorMessage;

    public ResponseAPI(String message, Integer code) {
        this.message = message;
        this.code = code;
    }
}
