package com.project.shop.model.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor

@Data
public class UserDto {

    @Email
    @NotBlank(message = "Email must be not null")
    private String email;

    @NotBlank(message = "Password must be not null")
    @Size(min = 5, max = 20, message = "Password must contain at least {min} to {max} characters")
    private String password;

    @NotBlank(message = "Password must be not null")
    private String state;

    @NotBlank(message = "Password must be not null")
    private String city;

    @NotBlank(message = "Password must be not null")
    private String adress;






}
