package com.example.authservice.service;

import com.example.authservice.dto.request.AccountCreationRequest;
import com.example.authservice.dto.request.AccountUpdateRequest;
import com.example.authservice.dto.response.AccountResponse;
import com.example.authservice.entity.Account;
import com.example.authservice.entity.Role;
import com.example.authservice.exception.AppException;
import com.example.authservice.exception.ErrorCode;
import com.example.authservice.mapper.AccountMapper;
import com.example.authservice.repository.AccountRepository;
import com.example.authservice.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AccountService {
    AccountRepository accountRepository;
    RoleRepository roleRepository;
//    ProfileClient profileClient;
    AccountMapper accountMapper;
    PasswordEncoder passwordEncoderBCrypt = new BCryptPasswordEncoder(10);
    public AccountResponse createAccount(AccountCreationRequest accountCreationRequest){
        if(accountRepository.existsByUsername(accountCreationRequest.getUsername())) throw new AppException(ErrorCode.USER_EXISTED);
        Account account = accountMapper.toAccount(accountCreationRequest);
        account.setPassword(passwordEncoderBCrypt.encode(accountCreationRequest.getPassword()));

        HashSet<Role> roles = new HashSet<>();
        account.setRoles(roles);

        account = accountRepository.save(account);
        //thieu profile mapper
        return accountMapper.toAccountResponse(account);
    }

//    xem thong tin tai khoan
    public AccountResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        Account account = accountRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return accountMapper.toAccountResponse(account);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public AccountResponse updateUser(String userId, AccountUpdateRequest request) {
        Account account = accountRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        accountMapper.updateAccount(account, request);
        account.setPassword(passwordEncoderBCrypt.encode(request.getPassword()));

        var roles = roleRepository.findAllById(request.getRoles());
        account.setRoles(new HashSet<>(roles));

        return accountMapper.toAccountResponse(accountRepository.save(account));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(String userId) {
        accountRepository.deleteById(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<AccountResponse> getUsers() {
//        log.info("In method get Users");
        return accountRepository.findAll().stream().map(accountMapper::toAccountResponse).toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public AccountResponse getUser(String id) {
        return accountMapper.toAccountResponse(
                accountRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

}
