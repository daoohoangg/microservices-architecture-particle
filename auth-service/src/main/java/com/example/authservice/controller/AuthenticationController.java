package com.example.authservice.controller;

import com.example.authservice.dto.request.AccountCreationRequest;
import com.example.authservice.dto.request.AuthenticationRequest;
import com.example.authservice.dto.request.IntrospectRequest;
import com.example.authservice.dto.response.AccountResponse;
import com.example.authservice.dto.response.ApiResponse;
import com.example.authservice.dto.response.AuthenticationResponse;
import com.example.authservice.dto.response.IntrospectResponse;
import com.example.authservice.service.AccountService;
import com.example.authservice.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;
    AccountService accountService;
    @PostMapping("/log-in")
    ApiResponse<AuthenticationResponse>authenticationLogin(@RequestBody AuthenticationRequest authenticationRequest){
        var result = authenticationService.authenticate(authenticationRequest);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest introspectRequest) throws JOSEException, ParseException {
        var result = authenticationService.introspect(introspectRequest);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }
    @PostMapping("/registration")
    ApiResponse<AccountResponse> createUser(@RequestBody @Valid AccountCreationRequest request) {
        return ApiResponse.<AccountResponse>builder()
                .result(accountService.createAccount(request))
                .build();
    }
}
