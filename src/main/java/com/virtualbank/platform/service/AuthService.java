package com.virtualbank.platform.service;

import com.virtualbank.platform.dto.RegisterRequestDto;

public interface AuthService {

    String register(RegisterRequestDto request);

}