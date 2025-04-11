package com.example.authservice.mapper;
import com.example.authservice.dto.request.AccountCreationRequest;
import com.example.authservice.dto.request.AccountUpdateRequest;
import com.example.authservice.dto.response.AccountResponse;
import com.example.authservice.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toAccount(AccountCreationRequest request);
    AccountResponse toAccountResponse(Account account);

    @Mapping(target = "roles", ignore = true)
    void updateAccount(@MappingTarget Account user, AccountUpdateRequest request);
}
