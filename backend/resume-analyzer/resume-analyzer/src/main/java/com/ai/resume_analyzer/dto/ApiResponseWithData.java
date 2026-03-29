package com.ai.resume_analyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponseWithData<T> {

    private boolean success;
    private T data;
    private String message;
}