package com.udemy.accounts.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ErrorResponsedto {
    private String apiPath;
    private HttpStatus errorcode;
    private String errorMsg;
    private LocalDateTime errorTime;
}
