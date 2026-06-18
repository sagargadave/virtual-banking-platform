package com.virtualbank.platform.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequestDto {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String phone;

    private String address;

    private LocalDate dateOfBirth;
}