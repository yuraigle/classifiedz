package ru.orlovs.classifiedz.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @Email(message = "email.malformed")
    @NotBlank(message = "email.required")
    private String email;

    @NotBlank(message = "password.required")
    @Size(min = 6, max = 20, message = "password.length")
    private String password;

    @NotBlank(message = "name.required")
    @Size(min = 3, max = 255, message = "name.length")
    private String name;
}
