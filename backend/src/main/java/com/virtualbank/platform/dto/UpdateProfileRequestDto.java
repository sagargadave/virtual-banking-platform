package com.virtualbank.platform.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfileRequestDto {

    private String firstName;

    private String lastName;

    private String phone;

    private String address;
}