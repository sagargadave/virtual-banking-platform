package com.virtualbank.platform.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatementRequestDto {

    private String fromDate;

    private String toDate;
}