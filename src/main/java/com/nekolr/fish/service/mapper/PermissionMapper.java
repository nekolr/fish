package com.nekolr.fish.service.mapper;

import com.nekolr.fish.entity.Permission;
import com.nekolr.fish.service.EntityMapper;
import com.nekolr.fish.service.dto.PermissionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PermissionMapper extends EntityMapper<Permission, PermissionDTO> {

}
