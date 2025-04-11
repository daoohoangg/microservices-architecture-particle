package com.example.authservice.service;

import com.example.authservice.dto.request.AuthenticationRequest;
import com.example.authservice.dto.response.AuthenticationResponse;
import com.example.authservice.exception.AppException;
import com.example.authservice.exception.ErrorCode;
import com.example.authservice.repository.AccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationService {
    AccountRepository accountRepository;
    public AuthenticationResponse authenticate(AuthenticationRequest request){
        var user = accountRepository.findByUsername(request.getUsername()).orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));
        if(user == null){
            System.out.println(1);
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        return new AuthenticationResponse(authenticated);
    }
}
