package com.springboot.expenseapi.entity;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class FieldErrorObject {
    private Integer statusCode;
    private String message;
    private String description;
    private Date timestamp;
    private Map<String, String> errors;
}
