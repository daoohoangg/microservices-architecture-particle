package com.example.authservice.mapper;

import com.example.authservice.dto.request.RoleRequest;
import com.example.authservice.dto.response.RoleResponse;
import com.example.authservice.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
