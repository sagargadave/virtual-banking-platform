package com.virtualbank.platform.controller;

import com.virtualbank.platform.dto.RegisterRequestDto;
import com.virtualbank.platform.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequestDto request) {

        return authService.register(request);
    }
}