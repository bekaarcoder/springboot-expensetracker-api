package com.springboot.expenseapi.entity;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserUpdateModel {

    @Pattern(regexp = "^(?!\\s*$).+", message = "must not be blank")
    private String name;

    @Pattern(regexp = "^(?!\\s*$).+", message = "must not be blank")
    @Email(message = "Enter a valid email")
    private String email;

    @Pattern(regexp = "^(?!\\s*$).+", message = "must not be blank")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    private Long age = 0L;
}
