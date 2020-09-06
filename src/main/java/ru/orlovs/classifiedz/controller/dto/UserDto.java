package ru.orlovs.classifiedz.controller.dto;

import lombok.Data;

@Data
public class UserDto {

    private final Long id;
    private final String email;
    private final String role;
}
