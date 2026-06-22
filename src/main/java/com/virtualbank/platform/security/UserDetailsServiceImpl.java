package com.virtualbank.platform.security;

import com.virtualbank.platform.exception.ResourceNotFoundException;
import com.virtualbank.platform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl
        implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        return userRepository.findByEmail(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Account not found"
                        ));
    }
}