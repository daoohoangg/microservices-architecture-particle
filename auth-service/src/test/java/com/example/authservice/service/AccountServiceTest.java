package com.example.authservice.service;

import com.example.authservice.dto.request.AccountCreationRequest;
import com.example.authservice.dto.response.AccountResponse;
import com.example.authservice.entity.Account;
import com.example.authservice.mapper.AccountMapper;
import com.example.authservice.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountMapper accountMapper;

    @InjectMocks
    private AccountService accountService;

    @Test
    void createAccount() {
        // Arrange
        AccountCreationRequest request = new AccountCreationRequest();
        request.setUsername("john_doe");
        request.setPassword("securePassword123");
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setDob(LocalDate.of(1990, 1, 1));
        request.setCity("New York");

        Account mockAccount = new Account();
        mockAccount.setUsername(request.getUsername());

        Account savedAccount = new Account();
        savedAccount.setId("1");
        savedAccount.setUsername("john_doe");

        AccountResponse expectedResponse = new AccountResponse();
        expectedResponse.setId("1");
        expectedResponse.setUsername("john_doe");

        when(accountRepository.existsByUsername("john_doe")).thenReturn(false);
        when(accountMapper.toAccount(request)).thenReturn(mockAccount);
        when(accountRepository.save(any(Account.class))).thenReturn(savedAccount);
        when(accountMapper.toAccountResponse(savedAccount)).thenReturn(expectedResponse);

        // Act
        AccountResponse actualResponse = accountService.createAccount(request);

        // Assert
        assertNotNull(actualResponse);
        assertEquals(expectedResponse.getUsername(), actualResponse.getUsername());
        verify(accountRepository).save(any(Account.class));
        verify(accountMapper).toAccount(request);
        verify(accountMapper).toAccountResponse(savedAccount);
    }
}