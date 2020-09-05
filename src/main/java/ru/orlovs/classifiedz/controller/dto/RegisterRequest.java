package ru.orlovs.classifiedz.controller.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegisterRequest {

    @Email(message = "email.malformed")
    @NotNull(message = "required")
    private String email;

    @NotNull(message = "required")
    @Size(min = 6, max = 20)
    private String password;

    @NotBlank(message = "required")
    @Size(min = 3, max = 255)
    private String name;
}
