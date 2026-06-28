package com.virtualbank.platform.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProfileResponseDto {

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String address;
}