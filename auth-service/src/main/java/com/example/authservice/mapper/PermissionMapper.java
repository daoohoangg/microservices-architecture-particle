package com.example.authservice.mapper;

import com.example.authservice.dto.request.PermissionRequest;
import com.example.authservice.dto.response.PermissionResponse;
import com.example.authservice.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
