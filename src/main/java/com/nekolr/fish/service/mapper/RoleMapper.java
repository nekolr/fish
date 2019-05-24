package com.nekolr.fish.service.mapper;

import com.nekolr.fish.entity.Role;
import com.nekolr.fish.service.EntityMapper;
import com.nekolr.fish.service.dto.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {PermissionMapper.class, MenuMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper extends EntityMapper<RoleDTO, Role> {

}
