package ru.orlovs.classifiedz.controller.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginRequest {

    @Email(message = "email.malformed")
    @NotBlank(message = "email.required")
    private String email;

    @NotBlank(message = "password.required")
    @Size(min = 6, max = 20, message = "password.length")
    private String password;
}
